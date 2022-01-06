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
import ro.nicolaemariusghergu.queryit.service.CategoryService;
import ro.nicolaemariusghergu.queryit.service.ManufacturerService;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
public class ExecutorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandler.class);

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    public static void downloadData(String url, String name) throws IOException {
        URL website = new URL(url);
        String resourcesPath = "src/main/resources/";
        String dataPath = resourcesPath + "data/";

        // I don't want to download the data if already exists
        String filePath = dataPath + name + ".json";
        File file = new File(filePath);
        if (file.exists()) {
            LOGGER.info("Data about " + name + " already exists on local!");
            return;
        }
        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream())) {
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }
    }

    @PostConstruct
    private void init() throws JSONException, IOException {
        LOGGER.info("ExecutorHandler has started. Starting collecting the data...");
        handleDataFromWeb();
    }

    private void handleDataFromWeb() throws JSONException, IOException {
        // hardcoded website to get mega image products from promotions, I need some data , lol , i stole some from you guys, sorry
        String baseLink = "https://api.mega-image.ro/?operationName=GetCategoryProductSearch&variables=%7B%22lang%22%3A%22ro%22%2C%22searchQuery%22%3A%22%22%2C%22category%22%3A%220--%22%2C%22pageNumber%22%3A0%2C%22pageSize%22%3A20%2C%22filterFlag%22%3Atrue%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bdb32b6dc09b8ee0bce785e0a6799c6ff2593a8d1fd2a4ad2ac66cf3e999fdf5%22%7D%7D";
        String baseLinkImages = "https://d1lqpgkqcok0l.cloudfront.net";

        Set<String> manufacturersName = new HashSet<>();

        long productId = 0L;
        long manufacturerId = 0L;
        long categoryId = 0L;
        // deoarece avem 15 categorii
        for (int i = 1; i <= 15; i++) {
            String word;
            if (i >= 10) {
                word = "" + i;
            } else {
                word = "0" + i;
            }

            System.out.println("Avem site-ul numarul " + i);
            String dataLink = baseLink.replace("--", word);
            System.out.println("Site-ul este " + dataLink);
            JSONObject jsonObject = readJsonFromUrl(dataLink);

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

            ExecutorHandler.downloadData(dataLink, categoryName);

            LOGGER.info("Get data about every Category...");
            // daca nu exista categoria in database
            if (categoryService.findByName(categoryName).isEmpty()) {
                // atunci creez un nou obiect
                Category category = new Category();
                category.setId(categoryId++);
                category.setName(categoryName);
                // si il salvez in database
                categoryService.saveAndFlush(category);
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
                        manufacturerService.saveAndFlush(manufacturer);
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

                    productService.saveAndFlush(product);
                    System.out.println(product);
                }
            }
        }

        LOGGER.info("Saved collected data to database!");

        //TODO: LOGGER.info("Randomizing data for other tables....");
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

}
