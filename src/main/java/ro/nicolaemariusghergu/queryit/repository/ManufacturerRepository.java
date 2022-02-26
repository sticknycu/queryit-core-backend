package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @NonNull
    @Override
    Optional<Manufacturer> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Manufacturer> findAll();

    Optional<Manufacturer> findByName(String name);

    @NonNull
    @Override
    <S extends Manufacturer> S save(@NonNull S entity);

    @NonNull
    @Override
    <S extends Manufacturer> List<S> saveAll(@NonNull Iterable<S> entities);

}
