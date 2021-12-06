package ro.nicolaemariusghergu.queryit.service.data;

import ro.nicolaemariusghergu.queryit.model.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Optional<Product> findByName(String name);
}
