package ro.nicolaemariusghergu.queryit.threads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper;
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper;
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper;
import ro.nicolaemariusghergu.queryit.proxy.ProductProxy;
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository;
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository;

@Slf4j
@Configuration
@EnableScheduling
public class UpdateDatabaseScheduler {

    private final ProductProxy productProxy;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PromotionRepository promotionRepository;
    private final ManufacturerRepository manufacturerRepository;

    public UpdateDatabaseScheduler(ProductProxy productProxy,
                                   ProductRepository productRepository,
                                   CategoryRepository categoryRepository,
                                   PromotionRepository promotionRepository,
                                   ManufacturerRepository manufacturerRepository) {
        this.productProxy = productProxy;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.promotionRepository = promotionRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60) // 1 hour
    public void run() {
        log.info("Starting saving Products informations to database, including Categories and Promotions...");

        log.info("Saving Categories to database...");
        var categoriesProxy = productProxy.getCategoriesFromMegaImage().getBody();
        var categoriesExists = categoryRepository.findAll();
        categoryRepository.saveAll(categoriesProxy.stream()
                .map(CategoryMapper.INSTANCE::categoryDtoToCategory)
                .filter(categoryProxy -> !categoriesExists.contains(categoryProxy))
                .toList());

        log.info("Saving Manufacturers to database...");
        var manufacturersProxy = productProxy.getManufacturersFromMegaImage().getBody();
        var manufacturersExists = manufacturerRepository.findAll();
        manufacturerRepository.saveAll(manufacturersProxy.stream()
                .filter(manufacturer -> !manufacturersExists.contains(manufacturer))
                .toList());

        log.info("Saving Products to database...");
        var proxyProducts = productProxy.getProductsFromMegaImage().getBody();
        var productsExists = productRepository.findAll();
        productRepository.saveAll(proxyProducts.stream()
                .map(ProductMapper.INSTANCE::productDtoToProduct)
                .filter(proxyProduct -> !productsExists.contains(proxyProduct))
                .toList());


        log.info("Saving Promotions to database..");
        var proxyPromotions = productProxy.getPromotionsFromMegaImage().getBody();
        var promotionsExists = promotionRepository.findAll();
        promotionRepository.saveAll(proxyPromotions.stream()
                .map(PromotionMapper.INSTANCE::promotionDtoToPromotion)
                .filter(proxyPromotion -> !promotionsExists.contains(proxyPromotion))
                .toList());

        log.info("Updating Products with promotions..");
        var productsWithPromotionsProxy = productProxy.getProductsWithPromotionFromMegaImage().getBody();
        var productsWithPromotionsExists = productRepository.findAll()
                        .stream().filter(product -> product.getPromotion() != null)
                        .toList();
        productRepository.saveAll(productsWithPromotionsProxy.stream()
                .map(ProductMapper.INSTANCE::productDtoToProduct)
                .filter(productWithPromotionProxy -> !productsWithPromotionsExists.contains(productWithPromotionProxy))
                .toList());

        log.info("Update DONE!");
    }

}
