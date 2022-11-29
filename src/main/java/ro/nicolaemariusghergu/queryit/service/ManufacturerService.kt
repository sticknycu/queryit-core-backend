package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto

interface ManufacturerService {
    open fun findManufacturerById(id: Long?): ResponseEntity<ManufacturerDto?>?
    open fun getManufacturers(): ResponseEntity<MutableList<ManufacturerDto?>?>?
    open fun addManufacturer(manufacturerDto: ManufacturerDto?): ResponseEntity<Long?>?
    open fun getManufacturerByName(name: String?): ResponseEntity<ManufacturerDto?>?
    open fun deleteManufacturerById(id: Long?): ResponseEntity<Long?>?
    open fun updateManufacturer(manufacturerDto: ManufacturerDto?): ResponseEntity<ManufacturerDto?>?
}