package ro.nicolaemariusghergu.queryit.executor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper;
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.service.CategoryService;
import ro.nicolaemariusghergu.queryit.service.ManufacturerService;
import ro.nicolaemariusghergu.queryit.service.ProductService;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public record ExecutorHandler(ProductService productService,
                              CategoryService categoryService,
                              PromotionService promotionService,
                              ManufacturerService manufacturerService) {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String DATA_PATH = "data/";

    @PostConstruct
    private void init() {
        log.info("ExecutorHandler has started. Starting collecting the data...");
        handleDataFromWeb();
    }

    private Map<String, JSONObject> readFiles() throws IOException, JSONException {
        File resourcesJsonFolder = new File(RESOURCES_PATH + DATA_PATH);
        Map<String, JSONObject> jsonData = new HashMap<>();
        for (final File fileEntry : Objects.requireNonNull(resourcesJsonFolder.listFiles())) {
            jsonData.put(fileEntry.getName(), readJSON(fileEntry.getName()));
        }
        return jsonData;
    }

    private JSONObject readJSON(String filename) throws IOException, JSONException {
        String stringData = new String(Files.readAllBytes(Path.of(RESOURCES_PATH + DATA_PATH + filename)));

        JSONObject jsonObject = new JSONObject(stringData);
        return jsonObject;
    }

    @SneakyThrows
    private void handleDataFromWeb() {
        String baseLinkImages = "https://d1lqpgkqcok0l.cloudfront.net";

        Set<String> manufacturersName = new HashSet<>();

        long productId = 0L;
        long manufacturerId = 0L;
        long categoryId = 0L;

        for (Map.Entry<String, JSONObject> entry : readFiles().entrySet()) {

            JSONObject jsonObject = entry.getValue();

            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("categoryProductSearch").getJSONArray("products");

            String categoryName = jsonObject.getJSONObject("data")
                    .getJSONObject("categoryProductSearch")
                    .getJSONArray("categorySearchTree")
                    .getJSONObject(0)
                    .getJSONArray("categoryDataList")
                    .getJSONObject(0)
                    .getJSONObject("categoryData")
                    .getJSONObject("facetData")
                    .get("name")
                    .toString();

            // If already data exists, I don't want to download it again
            //if (!ExecutorHandler.downloadData(dataLink, categoryName)) {
            //    continue;
            //}

            log.info("Get data about every Category...");
            // daca nu exista categoria in database
            try {
                if (categoryService.getCategoryByName(categoryName).getBody().getName().isEmpty()) {
                    // atunci creez un nou obiect
                    Category category = new Category();
                    category.setId(categoryId++);
                    category.setName(categoryName);

                    // si il salvez in database
                    categoryService.addCategory(CategoryMapper.INSTANCE
                            .categoryToCategoryDto(category));
                }

                // pentru fiecare produs
                for (int j = 0; j < jsonArray.length(); j++) {
                    // aflu manufacturer-ul
                    String manufacturerName = jsonArray
                            .getJSONObject(j)
                            .getString("manufacturerName");

                    // daca nu exista in database
                    if (manufacturerService.findByName(manufacturerName).isEmpty()) {
                        // si pentru ca se poate duplica, pentru ca pot avea 2 produse din categorii diferite
                        // cu acelasi manufacturer...
                        if (manufacturersName.add(manufacturerName)) {
                            // atunci creez un nou obiect
                            Manufacturer manufacturer = new Manufacturer();
                            manufacturer.setId(manufacturerId++);
                            manufacturer.setName(manufacturerName);

                            // si il salvez in baza de date
                            manufacturerService.save(manufacturer);
                        }
                    }
                }

                log.info("");
                log.info("Pentru categoria " + categoryName + " avem urmatoarele produse:");
                for (int j = 0; j < jsonArray.length(); j++) {
                    String productName = jsonArray
                            .getJSONObject(j)
                            .getString("name");

                    String urlImage = jsonArray.getJSONObject(j)
                            .getJSONArray("images")
                            .getJSONObject(2) // the correct array for url icon
                            .getString("url");
                    String iconUrl = baseLinkImages + urlImage;

                    // daca nu gasesc item-ul in database
                    if (productService.getProductByName(productName).getName().isEmpty()) {
                        Double price = jsonArray
                                .getJSONObject(j)
                                .getJSONObject("price")
                                .getDouble("value");


                        String manufacturerName = jsonArray
                                .getJSONObject(j)
                                .getString("manufacturerName");

                        try {
                            Category category = CategoryMapper.INSTANCE
                                    .categoryDtoToCategory(categoryService.getCategoryByName(categoryName).getBody());

                            Product product = new Product();
                            product.setId(productId++);
                            product.setName(productName);
                            product.setPrice(price);
                            product.setQuantity(new IntegerRangeRandomizer(1, 50).getRandomValue());
                            product.setCategory(category);
                            product.setIconUrl(iconUrl);

                            try {
                                Manufacturer manufacturer = manufacturerService.findByName(manufacturerName).get();
                                product.setManufacturer(manufacturer);
                            } catch (Exception e) {
                                log.info("Manufacturer not found for product= " + product);
                            }

                            productService.addProduct(
                                    ProductMapper.INSTANCE.productToProductDto(product));
                            log.info(String.valueOf(product));
                        } catch (NoSuchElementException noSuchElementException) {
                            log.info("Category has not been found for productName= " + productName);
                        }
                    }
                }
            } catch (NoSuchElementException noSuchElementException) {
                log.info("Category has not been found for product= " + productId);
            }
        }

        log.info("Saved collected data to database!");

        log.info("Randomizing table Promotion....");
        int maxValue = new IntegerRangeRandomizer(1, 20).getRandomValue();
        for (int i = 0; i < maxValue; i++) {
            try {
                ResponseEntity<PromotionDto> localPromotion = promotionService.getPromotionById((long) i);
                if (localPromotion.getBody() != null) {
                    log.info("Promotion with id " + i + " already exists in our database!");
                }
            } catch (NoSuchElementException noSuchElementException) {
                PromotionDto promotion = new PromotionDto();
                promotion.setId((long) i);

                int quantityNeeded = new IntegerRangeRandomizer(1, 3).getRandomValue();

                ProductDto neededProduct = findAvailableProductFromRandomizedIntegerId();
                promotion.setQuantityNeeded(quantityNeeded);

                String description = "La " +
                        neededProduct.getQuantity() +
                        " " +
                        neededProduct.getName() +
                        " primesti inca unul gratuit!";

                promotion.setProductId(neededProduct);
                promotion.setDescription(description);
                promotion.setName(neededProduct.getName());

                int randomDay = new IntegerRangeRandomizer(1, 27).getRandomValue();
                int randomMonth = new IntegerRangeRandomizer(0, 1).getRandomValue();
                int randomHour = new IntegerRangeRandomizer(1, 24).getRandomValue();
                int randomMinute = new IntegerRangeRandomizer(1, 59).getRandomValue();
                int randomSecond = new IntegerRangeRandomizer(1, 59).getRandomValue();

                LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().getYear(),
                        LocalDateTime.now().getMonthValue() + randomMonth,
                        randomDay,
                        randomHour,
                        randomMinute,
                        randomSecond);

                Instant instant = Instant.now();
                ZoneId zoneId = ZoneId.systemDefault();
                ZoneOffset zoneOffset = zoneId.getRules().getOffset(instant);
                promotion.setExpireDate(localDateTime.toInstant(zoneOffset).toEpochMilli());

                log.info(String.valueOf(promotion));

                promotionService.addPromotion(promotion);
            }
        }
    }

    private static final int MAX_PRODUCT_ID = 290;

    private ProductDto findAvailableProductFromRandomizedIntegerId() {
        int productRandomizedId = new IntegerRangeRandomizer(0, MAX_PRODUCT_ID).getRandomValue();
        ProductDto productDto = new ProductDto();
        productDto.setId((long) productRandomizedId);
        try {
            ResponseEntity<ProductDto> product = productService.findProductById((long) productRandomizedId);
            if (product.getBody() != null) {
                AtomicBoolean existanceOffer = new AtomicBoolean(false);
                promotionService.getPromotions()
                        .getBody()
                        .forEach(promotion -> {
                            if (promotion.getId() == productRandomizedId) {
                                existanceOffer.set(true);
                            }
                        });
                if (existanceOffer.get()) {
                    return findAvailableProductFromRandomizedIntegerId();
                } else {
                    return product.getBody();
                }
            }
        } catch (NoSuchElementException e) {
            return productDto;
        }
        return productDto;
    }

}
