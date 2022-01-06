package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    List<Product> findAll();

    Optional<Product> findByName(String name);

    List<Product> findAllByPrice(Double price);

    <S extends Product> S save(S entity);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    <S extends Product> S saveAndFlush(S entity);

    <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);
}
