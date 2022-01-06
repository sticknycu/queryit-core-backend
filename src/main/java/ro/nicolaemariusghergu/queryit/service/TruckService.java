package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Truck;

import java.util.List;
import java.util.Optional;

public interface TruckService {

    @NonNull
    Optional<Truck> findById(@NonNull Long id);

    @NonNull
    List<Truck> findAll();

    Optional<Truck> findBySerialNumber(String serialNumber);
}
