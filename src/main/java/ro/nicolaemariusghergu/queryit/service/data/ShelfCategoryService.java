package ro.nicolaemariusghergu.queryit.service.data;

import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;

import java.util.List;
import java.util.Optional;

public interface ShelfCategoryService {

    Optional<ShelfCategory> findById(Long id);

    Optional<ShelfCategory> findByName(String name);

    List<ShelfCategory> findAll();
}
