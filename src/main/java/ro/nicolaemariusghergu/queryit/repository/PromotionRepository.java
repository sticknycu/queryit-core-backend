package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.nicolaemariusghergu.queryit.model.Promotion;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @NonNull
    @Override
    Optional<Promotion> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Promotion> findAll();

    List<Promotion> findByName(String name);

    @NonNull
    @Override
    <S extends Promotion> S save(@NonNull S entity);

    @NonNull
    @Override
    <S extends Promotion> List<S> saveAll(@NonNull Iterable<S> entities);

    @NonNull
    @Override
    void deleteById(@NonNull Long id);

}
