package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto
import ro.nicolaemariusghergu.queryit.mapper.ManufacturerMapper
import ro.nicolaemariusghergu.queryit.model.Manufacturer
import ro.nicolaemariusghergu.queryit.repository.ManufacturerRepository
import ro.nicolaemariusghergu.queryit.service.ManufacturerService

@Service
class ManufacturerServiceImpl(private val manufacturerRepository: ManufacturerRepository) : ManufacturerService {
    override fun getManufacturers(): ResponseEntity<MutableList<ManufacturerDto>> {
        return ResponseEntity.ok(manufacturerRepository.findAll()
                .stream()
                .map { manufacturer: Manufacturer -> ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(manufacturer) }
                .toList())
    }
}