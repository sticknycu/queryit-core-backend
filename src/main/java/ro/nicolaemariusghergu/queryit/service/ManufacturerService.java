package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {

    ResponseEntity<ManufacturerDto> findManufacturerById(Long id);

    ResponseEntity<List<ManufacturerDto>> getManufacturers();

    ResponseEntity<Long> addManufacturer(ManufacturerDto manufacturerDto);

    ResponseEntity<ManufacturerDto> getManufacturerByName(String name);

    ResponseEntity<Long> deleteManufacturerById(Long id);

    ResponseEntity<ManufacturerDto> updateManufacturer(ManufacturerDto manufacturerDto);
}
