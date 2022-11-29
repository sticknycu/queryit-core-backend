package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.service.TruckService

@Controller
@RequestMapping("/api/trucks")
class TruckController(private val truckService: TruckService) {
    @GetMapping("/v1")
    @ResponseBody
    fun getTrucks(): ResponseEntity<List<TruckDto>> {
        return truckService.getTrucks()
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addTruck(@RequestBody truckDto: TruckDto): ResponseEntity<Long> {
        return truckService.addTruck(truckDto)
    }

    @DeleteMapping("/v1/{truckId}")
    @ResponseBody
    fun deleteTruck(@PathVariable truckId: Long): ResponseEntity<Long> {
        return truckService.deleteTruckById(truckId)
    }
}