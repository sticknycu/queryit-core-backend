package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

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
}
