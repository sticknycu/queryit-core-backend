package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.TruckDto;
import ro.nicolaemariusghergu.queryit.service.TruckService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@Controller
@RequestMapping("/api/trucks")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
public record TruckController(TruckService truckService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<TruckDto>> getTrucks() {
        return truckService.getTrucks();
    }

    @GetMapping("/v1/{truckId}")
    @ResponseBody
    public ResponseEntity<TruckDto> getTruckById(@PathVariable Long truckId) {
        return truckService.findTruckById(truckId);
    }

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<TruckDto> getTruckBySerialNumber(@RequestBody TruckDto truckDto) {
        return truckService.getTruckBySerialNumber(truckDto.getSerialNumber());
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addTruck(@RequestBody TruckDto truckDto) {
        return truckService.addTruck(truckDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<TruckDto> updateTruck(@RequestBody TruckDto truckDto) {
        return truckService.updateTruck(truckDto);
    }

    @DeleteMapping("/v1/{truckId}")
    @ResponseBody
    public ResponseEntity<Long> deleteTruck(@PathVariable Long truckId) {
        return truckService.deleteTruckById(truckId);
    }
}
