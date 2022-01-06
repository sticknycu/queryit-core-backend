package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    @NonNull
    Optional<Manufacturer> findById(@NonNull Long id);

    @NonNull
    List<Manufacturer> findAll();

    Optional<Manufacturer> findByName(String name);

    <S extends Manufacturer> S save(S entity);

    <S extends Manufacturer> S saveAndFlush(S entity);

    <S extends Manufacturer> List<S> saveAll(Iterable<S> entities);

    <S extends Manufacturer> List<S> saveAllAndFlush(Iterable<S> entities);
}
