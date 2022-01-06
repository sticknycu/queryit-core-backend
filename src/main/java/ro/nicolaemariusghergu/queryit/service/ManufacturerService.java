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
}
