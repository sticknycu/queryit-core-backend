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

    Category update(Category category);

    Optional<Category> findByName(String name);

    <S extends Category> S save(S entity);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    @NonNull
    void deleteById(@NonNull Long id);
}
