package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    @NonNull
    Optional<Category> findById(@NonNull Long id);

    @NonNull
    List<Category> findAll();

    Optional<Category> findByName(String name);
}
