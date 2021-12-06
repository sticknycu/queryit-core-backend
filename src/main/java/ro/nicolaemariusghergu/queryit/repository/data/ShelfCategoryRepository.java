package ro.nicolaemariusghergu.queryit.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;

import java.util.Optional;

@Repository
public interface ShelfCategoryRepository extends JpaRepository<ShelfCategory, Long> {

    Optional<ShelfCategory> findByName(String name);
}
