package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto

@Controller
@RequestMapping("/api/manufacturers")
@CrossOrigin(BackEndApplication.FRONTEND_CORE_ADDRESS)
class ManufacturerController {
    @GetMapping("/v1")
    @ResponseBody
    fun getManufacturers(): ResponseEntity<MutableList<ManufacturerDto?>?>? {
        return manufacturerService.getManufacturers()
    }

    @GetMapping("/v1/{manufacturerId}")
    @ResponseBody
    fun getManufacturerById(@PathVariable manufacturerId: Long?): ResponseEntity<ManufacturerDto?>? {
        return manufacturerService.findManufacturerById(manufacturerId)
    }

    @GetMapping("/v1/manufacturer-name")
    @ResponseBody
    fun getManufacturerByName(@RequestBody manufacturerDto: ManufacturerDto?): ResponseEntity<ManufacturerDto?>? {
        return manufacturerService.getManufacturerByName(manufacturerDto.getName())
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addManufacturer(@RequestBody manufacturerDto: ManufacturerDto?): ResponseEntity<Long?>? {
        return manufacturerService.addManufacturer(manufacturerDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateManufacturer(@RequestBody manufacturerDto: ManufacturerDto?): ResponseEntity<ManufacturerDto?>? {
        return manufacturerService.updateManufacturer(manufacturerDto)
    }

    @DeleteMapping("/v1/{manufacturerId}")
    @ResponseBody
    fun deleteManufacturer(@PathVariable manufacturerId: Long?): ResponseEntity<Long?>? {
        return manufacturerService.deleteManufacturerById(manufacturerId)
    }
}