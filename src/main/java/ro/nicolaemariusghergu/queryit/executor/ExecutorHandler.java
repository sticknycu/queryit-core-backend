package ro.nicolaemariusghergu.queryit.executor;

import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.model.Promotion;
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

@Component
public class ExecutorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandler.class);

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String DATA_PATH = "data/";

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    ManufacturerService manufacturerService;

    @PostConstruct
    private void init() throws JSONException, IOException {
        LOGGER.info("ExecutorHandler has started. Starting collecting the data...");
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

    private void handleDataFromWeb() throws JSONException, IOException {
        // hardcoded website to get mega image products from promotions, I need some data , lol , i stole some from you guys, sorry
        //String baseLink = "https://api.mega-image.ro/?operationName=GetCategoryProductSearch&variables=%7B%22lang%22%3A%22ro%22%2C%22searchQuery%22%3A%22%22%2C%22category%22%3A%220--%22%2C%22pageNumber%22%3A0%2C%22pageSize%22%3A20%2C%22filterFlag%22%3Atrue%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bdb32b6dc09b8ee0bce785e0a6799c6ff2593a8d1fd2a4ad2ac66cf3e999fdf5%22%7D%7D";
        String baseLinkImages = "https://d1lqpgkqcok0l.cloudfront.net";

        Set<String> manufacturersName = new HashSet<>();

        long productId = 0L;
        long manufacturerId = 0L;
        long categoryId = 0L;
        /* deoarece avem 15 categorii
        for (int i = 1; i <= 15; i++) {
            String word;
            if (i >= 10) {
                word = "" + i;
            } else {
                word = "0" + i;
            }*

            System.out.println("Avem site-ul numarul " + i);
            String dataLink = baseLink.replace("--", word);
            System.out.println("Site-ul este " + dataLink);*/

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

            /* If already data exists, I don't want to download it again
            if (!ExecutorHandler.downloadData(dataLink, categoryName)) {
                continue;
            }*/

            LOGGER.info("Get data about every Category...");
            // daca nu exista categoria in database
            if (categoryService.findByName(categoryName).isEmpty()) {
                // atunci creez un nou obiect
                Category category = new Category();
                category.setId(categoryId++);
                category.setName(categoryName);

                // si il salvez in database
                categoryService.save(category);
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

            System.out.println();
            System.out.println("Pentru categoria " + categoryName + " avem urmatoarele produse:");
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
                if (productService.findByName(productName).isEmpty()) {
                    Double price = jsonArray
                            .getJSONObject(j)
                            .getJSONObject("price")
                            .getDouble("value");


                    String manufacturerName = jsonArray
                            .getJSONObject(j)
                            .getString("manufacturerName");

                    Category category = categoryService.findByName(categoryName).get();

                    Product product = new Product();
                    product.setId(productId++);
                    product.setName(productName);
                    product.setPrice(price);
                    product.setQuantity(new IntegerRangeRandomizer(1, 50).getRandomValue());
                    product.setCategory(category);
                    product.setIconUrl(iconUrl);

                    Manufacturer manufacturer = manufacturerService.findByName(manufacturerName).get();
                    product.setManufacturer(manufacturer);

                    productService.save(product);
                    System.out.println(product);
                }
            }
        }

        LOGGER.info("Saved collected data to database!");

        LOGGER.info("Randomizing table Promotion....");
        int maxValue = new IntegerRangeRandomizer(1, 20).getRandomValue();
        for (int i = 0; i < maxValue; i++) {
            Optional<Promotion> localPromotion = promotionService.findById((long) i);
            if (localPromotion.isPresent()) {
                LOGGER.info("Promotion with id " + i + " already exists in our database!");
                continue;
            }

            Promotion promotion = new Promotion();
            promotion.setId((long) i);

            int quantityNeeded = new IntegerRangeRandomizer(1, 3).getRandomValue();

            Product neededProduct = findAvailableProductFromRandomizedIntegerId();
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

            System.out.println(promotion);

            promotionService.save(promotion);
        }
    }

    private final int MAX_PRODUCT_ID = 290;

    private Product findAvailableProductFromRandomizedIntegerId() {
        int productRandomizedId = new IntegerRangeRandomizer(0, MAX_PRODUCT_ID).getRandomValue();
        Optional<Product> product = productService.findById((long) productRandomizedId);
        if (product.isEmpty()) {
            return findAvailableProductFromRandomizedIntegerId();
        } else {
            AtomicBoolean existanceOffer = new AtomicBoolean(false);
            promotionService.findAll()
                    .forEach(promotion -> {
                        if (promotion.getProductId().getId() == productRandomizedId) {
                            existanceOffer.set(true);
                        }
                    });
            if (existanceOffer.get()) {
                return findAvailableProductFromRandomizedIntegerId();
            } else {
                return product.get();
            }
        }
    }

}
