package ro.nicolaemariusghergu.queryit.service.impl.data;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void handleDataFromWeb() throws JSONException, IOException {
        // hardcoded website to get mega image products
        String website = "https://api.mega-image.ro/?operationName=QlProductList&variables=%7B%22productListingType%22%3A%22PROMOTION_SEARCH%22%2C%22lang%22%3A%22ro%22%2C%22productCodes%22%3A%22%22%2C%22categoryCode%22%3A%22%22%2C%22excludedProductCodes%22%3A%22%22%2C%22brands%22%3A%22%22%2C%22keywords%22%3A%22%22%2C%22productTypes%22%3A%22%22%2C%22numberOfItemsToDisplay%22%3A40%2C%22lazyLoadCount%22%3A10%2C%22pageNumber%22%3A0%2C%22sort%22%3A%22%22%2C%22searchQuery%22%3A%22%22%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%227002669566907d507d1d3697aee939727738ff5d8a3e07e42aa5f7ee21305cc7%22%7D%7D";
        JSONObject jsonObject = readJsonFromUrl(website);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("qlProductList").getJSONArray("products");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject productObject = jsonArray.getJSONObject(i);
            System.out.println("Name: " + productObject.get("name"));
        }
    }

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
}
