package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.TruckDto;
import ro.nicolaemariusghergu.queryit.mapper.TruckMapper;
import ro.nicolaemariusghergu.queryit.repository.TruckRepository;
import ro.nicolaemariusghergu.queryit.service.TruckService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record TruckServiceImpl(TruckRepository truckRepository) implements TruckService {

    @Override
    public ResponseEntity<TruckDto> findTruckById(Long id) {
        return ResponseEntity.ok(truckRepository.findById(id).stream()
                .map(TruckMapper.INSTANCE::truckToTruckDto)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Truck does not exist!"))
        );
    }

    @Override
    public ResponseEntity<List<TruckDto>> getTrucks() {
        return ResponseEntity.ok(truckRepository.findAll()
                .stream()
                .map(TruckMapper.INSTANCE::truckToTruckDto)
                .toList());
    }

    @Override
    public ResponseEntity<Long> addTruck(TruckDto truckDto) {
        truckRepository.save(TruckMapper.INSTANCE.truckDtoToTruck(truckDto));
        return ResponseEntity.ok(truckDto.getId());
    }

    @Override
    public ResponseEntity<TruckDto> getTruckBySerialNumber(String serialNumber) {
        return ResponseEntity.ok(TruckMapper.INSTANCE.truckToTruckDto(
                truckRepository.findBySerialNumber(serialNumber)
                        .orElseThrow(() ->
                                new NoSuchElementException("Truck cannot be found"))
        ));
    }

    @Override
    public ResponseEntity<Long> deleteTruckById(Long id) {
        truckRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<TruckDto> updateTruck(TruckDto truckDto) {
        TruckDto modifiedTruck = truckRepository.findById(truckDto.getId()).stream()
                .map(TruckMapper.INSTANCE::truckToTruckDto)
                .map(updatedTruck -> {
                    updatedTruck.setSerialNumber(truckDto.getSerialNumber());
                    return updatedTruck;
                })
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Truck has not been found")
                );
        truckRepository.save(
                TruckMapper.INSTANCE.truckDtoToTruck(modifiedTruck));
        return ResponseEntity.ok(modifiedTruck);
    }
}
