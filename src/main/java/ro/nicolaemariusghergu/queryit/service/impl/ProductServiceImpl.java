package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

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
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }
}
