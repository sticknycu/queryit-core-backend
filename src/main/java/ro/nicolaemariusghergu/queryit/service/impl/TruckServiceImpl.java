package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Truck;
import ro.nicolaemariusghergu.queryit.repository.TruckRepository;
import ro.nicolaemariusghergu.queryit.service.TruckService;

import java.util.List;
import java.util.Optional;

@Service
public record TruckServiceImpl(TruckRepository truckRepository) implements TruckService {

    @NonNull
    @Override
    public Optional<Truck> findById(@NonNull Long id) {
        return truckRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Truck> findAll() {
        return truckRepository.findAll();
    }

    @Override
    public Optional<Truck> findBySerialNumber(String serialNumber) {
        return truckRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public <S extends Truck> S save(S entity) {
        return truckRepository.save(entity);
    }

    @Override
    public <S extends Truck> List<S> saveAll(Iterable<S> entities) {
        return truckRepository.saveAll(entities);
    }
}
