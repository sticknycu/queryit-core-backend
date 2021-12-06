package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Shelf;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;
import ro.nicolaemariusghergu.queryit.repository.ShelfRepository;
import ro.nicolaemariusghergu.queryit.service.ShelfService;

import java.util.List;
import java.util.Optional;

@Service
public record ShelfServiceImpl(ShelfRepository shelfRepository) implements ShelfService {

    @Override
    public Optional<List<Shelf>> findAllByShelfCategory(ShelfCategory shelfCategory) {
        return shelfRepository.findAllByShelfCategory(shelfCategory);
    }

    @Override
    public Optional<List<Shelf>> findAllByProduct(Product product) {
        return shelfRepository.findAllByProduct(product);
    }

    @Override
    public List<Shelf> findAll() {
        return shelfRepository.findAll();
    }
}
