package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.TruckDto

@Controller
@RequestMapping("/api/trucks")
@CrossOrigin(BackEndApplication.FRONTEND_CORE_ADDRESS)
class TruckController {
    @GetMapping("/v1")
    @ResponseBody
    fun getTrucks(): ResponseEntity<MutableList<TruckDto?>?>? {
        return truckService.getTrucks()
    }

    @GetMapping("/v1/{truckId}")
    @ResponseBody
    fun getTruckById(@PathVariable truckId: Long?): ResponseEntity<TruckDto?>? {
        return truckService.findTruckById(truckId)
    }

    @GetMapping("/v1/truck-serialnumber")
    @ResponseBody
    fun getTruckBySerialNumber(@RequestBody truckDto: TruckDto?): ResponseEntity<TruckDto?>? {
        return truckService.getTruckBySerialNumber(truckDto.getSerialNumber())
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addTruck(@RequestBody truckDto: TruckDto?): ResponseEntity<Long?>? {
        return truckService.addTruck(truckDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateTruck(@RequestBody truckDto: TruckDto?): ResponseEntity<TruckDto?>? {
        return truckService.updateTruck(truckDto)
    }

    @DeleteMapping("/v1/{truckId}")
    @ResponseBody
    fun deleteTruck(@PathVariable truckId: Long?): ResponseEntity<Long?>? {
        return truckService.deleteTruckById(truckId)
    }
}