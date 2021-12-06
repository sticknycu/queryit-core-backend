package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Shelf;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    Optional<List<Shelf>> findAllByShelfCategory(ShelfCategory shelfCategory);

    Optional<List<Shelf>> findAllByProduct(Product product);
}
