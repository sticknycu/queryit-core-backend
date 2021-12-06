package ro.nicolaemariusghergu.queryit.service;

import ro.nicolaemariusghergu.queryit.model.Shelf;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;

import java.util.List;
import java.util.Optional;

public interface ShelfService {

    Optional<List<Shelf>> findAllByShelfCategory(ShelfCategory shelfCategory);

    Optional<List<Shelf>> findAllByProduct(Product product);

    List<Shelf> findAll();
}
