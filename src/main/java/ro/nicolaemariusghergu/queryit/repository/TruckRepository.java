package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Truck;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    @NonNull
    @Override
    Optional<Truck> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Truck> findAll();

    Optional<Truck> findBySerialNumber(String serialNumber);

    @NonNull
    @Override
    <S extends Truck> S save(@NonNull S entity);

    @Override
    <S extends Truck> List<S> saveAll(Iterable<S> entities);
}
