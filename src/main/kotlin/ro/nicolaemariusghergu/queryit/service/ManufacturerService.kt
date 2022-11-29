package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto


interface ManufacturerService {
    fun findManufacturerById(id: Long): ResponseEntity<ManufacturerDto>

    fun getManufacturers(): ResponseEntity<List<ManufacturerDto>>

    fun addManufacturer(manufacturerDto: ManufacturerDto): ResponseEntity<Long>

    fun getManufacturerByName(name: String): ResponseEntity<ManufacturerDto>

    fun deleteManufacturerById(id: Long): ResponseEntity<Long>

    fun updateManufacturer(manufacturerDto: ManufacturerDto): ResponseEntity<ManufacturerDto>
}