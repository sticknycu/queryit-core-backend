package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto;
import ro.nicolaemariusghergu.queryit.service.ManufacturerService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@Controller
@RequestMapping("/api/manufacturers")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
public record ManufacturerController(ManufacturerService manufacturerService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<ManufacturerDto>> getManufacturers() {
        return manufacturerService.getManufacturers();
    }

    @GetMapping("/v1/{manufacturerId}")
    @ResponseBody
    public ResponseEntity<ManufacturerDto> getManufacturerById(@PathVariable Long manufacturerId) {
        return manufacturerService.findManufacturerById(manufacturerId);
    }

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<ManufacturerDto> getManufacturerByName(@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerService.getManufacturerByName(manufacturerDto.getName());
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addManufacturer(@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerService.addManufacturer(manufacturerDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<ManufacturerDto> updateManufacturer(@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerService.updateManufacturer(manufacturerDto);
    }

    @DeleteMapping("/v1/{manufacturerId}")
    @ResponseBody
    public ResponseEntity<Long> deleteManufacturer(@PathVariable Long manufacturerId) {
        return manufacturerService.deleteManufacturerById(manufacturerId);
    }
}
