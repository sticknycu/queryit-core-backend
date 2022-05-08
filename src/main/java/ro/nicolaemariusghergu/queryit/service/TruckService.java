package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.TruckDto;

import java.util.List;

public interface TruckService {
    ResponseEntity<TruckDto> findTruckById(Long id);

    ResponseEntity<List<TruckDto>> getTrucks();

    ResponseEntity<Long> addTruck(TruckDto truckDto);

    ResponseEntity<TruckDto> getTruckBySerialNumber(String serialNumber);

    ResponseEntity<Long> deleteTruckById(Long id);

    ResponseEntity<TruckDto> updateTruck(TruckDto truckDto);
}
