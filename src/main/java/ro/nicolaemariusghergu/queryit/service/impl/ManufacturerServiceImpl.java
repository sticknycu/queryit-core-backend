package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository;
import ro.nicolaemariusghergu.queryit.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public record ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) implements ManufacturerService {

    @NonNull
    @Override
    public Optional<Manufacturer> findById(@NonNull Long id) {
        return manufacturerRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return manufacturerRepository.findByName(name);
    }

    @Override
    public <S extends Manufacturer> S save(S entity) {
        return manufacturerRepository.save(entity);
    }

    @Override
    public <S extends Manufacturer> List<S> saveAll(Iterable<S> entities) {
        return manufacturerRepository.saveAll(entities);
    }
}
