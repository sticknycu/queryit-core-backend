package ro.nicolaemariusghergu.queryit.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    List<Product> findAll();

    Optional<Product> findByName(String name);

    List<Product> findAllByPrice(Double price);

    void handleDataFromWeb() throws JSONException, IOException;
}
