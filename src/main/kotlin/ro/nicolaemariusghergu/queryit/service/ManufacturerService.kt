package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto

interface ManufacturerService {
    fun getManufacturers(): ResponseEntity<MutableList<ManufacturerDto>>
}