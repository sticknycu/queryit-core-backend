package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {

    @NonNull
    Optional<Promotion> findById(@NonNull Long id);

    @NonNull
    List<Promotion> findAll();

    List<Promotion> findByName(String name);

    <S extends Promotion> S save(S entity);

    <S extends Promotion> List<S> saveAll(Iterable<S> entities);

    @NonNull
    void deleteById(@NonNull Long id);

}
