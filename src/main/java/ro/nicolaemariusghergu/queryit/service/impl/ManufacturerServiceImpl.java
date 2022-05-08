package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto;
import ro.nicolaemariusghergu.queryit.mapper.ManufacturerMapper;
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository;
import ro.nicolaemariusghergu.queryit.service.ManufacturerService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) implements ManufacturerService {

    @Override
    public ResponseEntity<ManufacturerDto> findManufacturerById(Long id) {
        return ResponseEntity.ok(manufacturerRepository.findById(id).stream()
                .map(ManufacturerMapper.INSTANCE::manufacturerToManufacturerDto)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Manufacturer does not exist!"))
        );
    }

    @Override
    public ResponseEntity<List<ManufacturerDto>> getManufacturers() {
        return ResponseEntity.ok(manufacturerRepository.findAll()
                .stream()
                .map(ManufacturerMapper.INSTANCE::manufacturerToManufacturerDto)
                .toList());
    }

    @Override
    public ResponseEntity<Long> addManufacturer(ManufacturerDto manufacturerDto) {
        manufacturerRepository.save(ManufacturerMapper.INSTANCE.manufacturerDtoToManufacturer(manufacturerDto));
        return ResponseEntity.ok(manufacturerDto.getId());
    }

    @Override
    public ResponseEntity<ManufacturerDto> getManufacturerByName(String name) {
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(
                manufacturerRepository.findByName(name)
                        .orElseThrow(() ->
                                new NoSuchElementException("Manufacturer cannot be found"))
        ));
    }

    @Override
    public ResponseEntity<Long> deleteManufacturerById(Long id) {
        manufacturerRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<ManufacturerDto> updateManufacturer(ManufacturerDto manufacturerDto) {
        ManufacturerDto modifiedManufacturer = manufacturerRepository.findById(manufacturerDto.getId()).stream()
                .map(ManufacturerMapper.INSTANCE::manufacturerToManufacturerDto)
                .map(updatedManufacturer -> {
                    updatedManufacturer.setName(manufacturerDto.getName());
                    return updatedManufacturer;
                })
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Manufacturer has not been found")
                );
        manufacturerRepository.save(
                ManufacturerMapper.INSTANCE.manufacturerDtoToManufacturer(modifiedManufacturer));
        return ResponseEntity.ok(modifiedManufacturer);
    }
}
