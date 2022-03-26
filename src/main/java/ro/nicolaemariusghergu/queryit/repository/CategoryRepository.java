package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.nicolaemariusghergu.queryit.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @NonNull
    @Override
    Optional<Category> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Category> findAll();

    Optional<Category> findByName(String name);

    @NonNull
    @Override
    <S extends Category> S save(@NonNull S entity);

    @NonNull
    @Override
    <S extends Category> List<S> saveAll(@NonNull Iterable<S> entities);

    @NonNull
    @Override
    void deleteById(@NonNull Long id);
}
