package ro.nicolaemariusghergu.queryit.service.impl;

import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.executor.ExecutorHandler;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository;
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public record ProductServiceImpl(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository) implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandler.class);

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @NonNull
    @Override
    public Optional<Product> findById(@NonNull Long id) {
        return productRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAllByPrice(Double price) {
        return productRepository.findAllByPrice(price);
    }

    @Override
    public void handleDataFromWeb() throws JSONException, IOException {
        // hardcoded website to get mega image products from promotions, I need some data , lol , i stole some from you guys, sorry
        String baseLink = "https://api.mega-image.ro/?operationName=GetCategoryProductSearch&variables=%7B%22lang%22%3A%22ro%22%2C%22searchQuery%22%3A%22%22%2C%22category%22%3A%220--%22%2C%22pageNumber%22%3A0%2C%22pageSize%22%3A20%2C%22filterFlag%22%3Atrue%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bdb32b6dc09b8ee0bce785e0a6799c6ff2593a8d1fd2a4ad2ac66cf3e999fdf5%22%7D%7D";
        String baseLinkImages = "https://d1lqpgkqcok0l.cloudfront.net";

        Set<Category> categories = new HashSet<>();
        Set<Manufacturer> manufacturers = new HashSet<>();
        Set<Product> products = new HashSet<>();

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

            Category category = new Category();
            category.setId(categoryId++);
            category.setName(categoryName);

            categories.add(category);

            System.out.println();
            System.out.println("Pentru categoria " + categoryName + " avem urmatoarele produse:");
            for (int j = 0; j < jsonArray.length(); j++) {
                String productName = jsonArray
                        .getJSONObject(j)
                        .getString("name");

                Double price = jsonArray
                        .getJSONObject(j)
                        .getJSONObject("price")
                        .getDouble("value");

                String manufacturerName = jsonArray
                        .getJSONObject(j)
                        .getString("manufacturerName");

                String urlImage = jsonArray.getJSONObject(j)
                        .getJSONArray("images")
                        .getJSONObject(2) // the correct array for url icon
                        .getString("url");

                String iconUrl = baseLinkImages + urlImage;

                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(manufacturerId++);
                manufacturer.setName(manufacturerName);

                Product product = new Product();
                product.setId(productId++);
                product.setName(productName);
                product.setPrice(price);
                product.setQuantity(new IntegerRangeRandomizer(1, 50).getRandomValue());
                product.setCategory(category);
                product.setManufacturer(manufacturer);

                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setPrice(product.getPrice());
                productDto.setQuantity(product.getQuantity());
                productDto.setCategory(product.getCategory());
                productDto.setManufacturer(product.getManufacturer());
                productDto.setIconUrl(iconUrl);

                products.add(product);
                manufacturers.add(manufacturer);

                System.out.println(productDto);
            }
        }

        LOGGER.info("Saving collected data to database...");

        categories.forEach(category -> {
            if (categoryRepository.findByName(category.getName()).isEmpty()) {
                categoryRepository.save(category);
            }
        });
        manufacturers.forEach(manufacturer -> {
            if (manufacturerRepository.findByName(manufacturer.getName()).isEmpty()) {
                manufacturerRepository.save(manufacturer);
            }
        });
        products.forEach(product -> {
            if (productRepository.findByName(product.getName()).isEmpty()) {
                productRepository.save(product);
            }
        });
    }
}
