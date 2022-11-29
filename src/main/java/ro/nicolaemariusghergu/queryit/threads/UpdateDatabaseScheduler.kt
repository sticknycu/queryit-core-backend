package ro.nicolaemariusghergu.queryit.threads

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper
import ro.nicolaemariusghergu.queryit.model.Category
import ro.nicolaemariusghergu.queryit.model.Manufacturer
import ro.nicolaemariusghergu.queryit.model.Product
import ro.nicolaemariusghergu.queryit.model.Promotion
import ro.nicolaemariusghergu.queryit.proxy.ProductProxy
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository
import ro.nicolaemariusghergu.queryit.repository.ProductRepository
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository

@Configuration
@EnableScheduling
open class UpdateDatabaseScheduler(private val productProxy: ProductProxy,
                                   private val productRepository: ProductRepository,
                                   private val categoryRepository: CategoryRepository,
                                   private val promotionRepository: PromotionRepository,
                                   private val manufacturerRepository: ManufacturerRepository) {

    private val logger = KotlinLogging.logger {}

    @Scheduled(fixedRate = 1000 * 60 * 60) // 1 hour
    fun run() {
        logger.info { "Starting saving Products informations to database, including Categories and Promotions..." }
        logger.info { "Saving Categories to database..." }
        val categoriesProxy = productProxy.getCategoriesFromMegaImage()!!.body
        val categoriesExists = categoryRepository.findAll()
        categoryRepository.saveAll(categoriesProxy!!.stream()
                .map { categoryDto: CategoryDto? -> CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto) }
                .filter { categoryProxy: Category? -> !categoriesExists.contains(categoryProxy) }
                .toList())
        logger.info { "Saving Manufacturers to database..." }
        val manufacturersProxy = productProxy.getManufacturersFromMegaImage()!!.body
        val manufacturersExists = manufacturerRepository.findAll()
        manufacturerRepository.saveAll(manufacturersProxy!!.stream()
                .filter { manufacturer: Manufacturer? -> !manufacturersExists!!.contains(manufacturer) }
                .toList())
        logger.info { "Saving Products to database..." }
        val proxyProducts = productProxy.getProductsFromMegaImage()!!.body
        val productsExists = productRepository.findAll()
        productRepository.saveAll(proxyProducts!!.stream()
                .map { productDto: ProductDto? -> ProductMapper.INSTANCE.productDtoToProduct(productDto) }
                .filter { proxyProduct: Product? -> !productsExists.contains(proxyProduct) }
                .toList())
        logger.info { "Saving Promotions to database.." }
        val proxyPromotions = productProxy.getPromotionsFromMegaImage()!!.body
        val promotionsExists = promotionRepository.findAll()
        promotionRepository.saveAll(proxyPromotions!!.stream()
                .map { promotionDto: PromotionDto? -> PromotionMapper.INSTANCE.promotionDtoToPromotion(promotionDto) }
                .filter { proxyPromotion: Promotion? -> !promotionsExists.contains(proxyPromotion) }
                .toList())
        logger.info { "Updating Products with promotions.." }
        val productsWithPromotionsProxy = productProxy.getProductsWithPromotionFromMegaImage()!!.body
        val productsWithPromotionsExists = productRepository.findAll()
                .stream().filter { product: Product? -> product!!.promotion != null }
                .toList()
        productRepository.saveAll(productsWithPromotionsProxy!!.stream()
                .map { productDto: ProductDto? -> ProductMapper.INSTANCE.productDtoToProduct(productDto) }
                .filter { productWithPromotionProxy: Product? -> !productsWithPromotionsExists.contains(productWithPromotionProxy) }
                .toList())
        logger.info { "Update DONE!" }
    }
}