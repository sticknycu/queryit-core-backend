package ro.nicolaemariusghergu.queryit.threads

import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper
import ro.nicolaemariusghergu.queryit.model.*
import ro.nicolaemariusghergu.queryit.proxy.ProductProxy
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository
import ro.nicolaemariusghergu.queryit.repository.ProductRepository
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository
import java.util.function.Function

@Slf4j
@Configuration
@EnableScheduling
open class UpdateDatabaseScheduler(private val productProxy: ProductProxy?,
                                   private val productRepository: ProductRepository?,
                                   private val categoryRepository: CategoryRepository?,
                                   private val promotionRepository: PromotionRepository?,
                                   private val manufacturerRepository: ManufacturerRepository?) {

    @Scheduled(fixedRate = 1000 * 60 * 60) // 1 hour
    fun run() {
        UpdateDatabaseScheduler.log.info("Starting saving Products informations to database, including Categories and Promotions...")
        UpdateDatabaseScheduler.log.info("Saving Categories to database...")
        val categoriesProxy = productProxy.getCategoriesFromMegaImage().body
        val categoriesExists = categoryRepository.findAll()
        categoryRepository.saveAll(categoriesProxy.stream()
                .map(Function<CategoryDto?, Category?> { categoryDto: CategoryDto? -> CategoryMapper.Companion.INSTANCE.categoryDtoToCategory(categoryDto) })
                .filter { categoryProxy: Category? -> !categoriesExists.contains(categoryProxy) }
                .toList())
        UpdateDatabaseScheduler.log.info("Saving Manufacturers to database...")
        val manufacturersProxy = productProxy.getManufacturersFromMegaImage().body
        val manufacturersExists = manufacturerRepository.findAll()
        manufacturerRepository.saveAll(manufacturersProxy.stream()
                .filter { manufacturer: Manufacturer? -> !manufacturersExists.contains(manufacturer) }
                .toList())
        UpdateDatabaseScheduler.log.info("Saving Products to database...")
        val proxyProducts = productProxy.getProductsFromMegaImage().body
        val productsExists = productRepository.findAll()
        productRepository.saveAll(proxyProducts.stream()
                .map(Function<ProductDto?, Product?> { productDto: ProductDto? -> ProductMapper.Companion.INSTANCE.productDtoToProduct(productDto) })
                .filter { proxyProduct: Product? -> !productsExists.contains(proxyProduct) }
                .toList())
        UpdateDatabaseScheduler.log.info("Saving Promotions to database..")
        val proxyPromotions = productProxy.getPromotionsFromMegaImage().body
        val promotionsExists = promotionRepository.findAll()
        promotionRepository.saveAll(proxyPromotions.stream()
                .map(Function<PromotionDto?, Promotion?> { promotionDto: PromotionDto? -> PromotionMapper.Companion.INSTANCE.promotionDtoToPromotion(promotionDto) })
                .filter { proxyPromotion: Promotion? -> !promotionsExists.contains(proxyPromotion) }
                .toList())
        UpdateDatabaseScheduler.log.info("Updating Products with promotions..")
        val productsWithPromotionsProxy = productProxy.getProductsWithPromotionFromMegaImage().body
        val productsWithPromotionsExists = productRepository.findAll()
                .stream().filter { product: Product? -> product.getPromotion() != null }
                .toList()
        productRepository.saveAll(productsWithPromotionsProxy.stream()
                .map(Function<ProductDto?, Product?> { productDto: ProductDto? -> ProductMapper.Companion.INSTANCE.productDtoToProduct(productDto) })
                .filter { productWithPromotionProxy: Product? -> !productsWithPromotionsExists.contains(productWithPromotionProxy) }
                .toList())
        UpdateDatabaseScheduler.log.info("Update DONE!")
    }
}