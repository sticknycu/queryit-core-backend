package ro.nicolaemariusghergu.queryit.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    @Override
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Product> findAll();

    Optional<Product> findByName(String name);

    List<Product> findAllByPrice(Double price);

    @NonNull
    @Override
    <S extends Product> S save(@NonNull S entity);

    @NonNull
    @Override
    <S extends Product> List<S> saveAll(@NonNull Iterable<S> entities);

    @NonNull
    @Override
    void deleteById(@NotNull Long id);

    List<Product> findAllByCategoryId(Long categoryId);
}
