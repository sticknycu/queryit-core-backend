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
    override fun findManufacturerById(id: Long): ResponseEntity<ManufacturerDto> {
        return ResponseEntity.ok(manufacturerRepository.findById(id).stream()
                .map { manufacturer: Manufacturer -> ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(manufacturer) }
                .findFirst()
                .orElseThrow { NoSuchElementException("Manufacturer does not exist!") }
        )
    }

    override fun getManufacturers(): ResponseEntity<List<ManufacturerDto>> {
        return ResponseEntity.ok(manufacturerRepository.findAll()
                .stream()
                .map { manufacturer: Manufacturer -> ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(manufacturer) }
                .toList())
    }

    override fun addManufacturer(manufacturerDto: ManufacturerDto): ResponseEntity<Long> {
        manufacturerRepository.save(ManufacturerMapper.INSTANCE.manufacturerDtoToManufacturer(manufacturerDto))
        return ResponseEntity.ok(manufacturerDto.id)
    }

    override fun getManufacturerByName(name: String): ResponseEntity<ManufacturerDto> {
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(
                manufacturerRepository.findByName(name)
                        .orElseThrow { NoSuchElementException("Manufacturer cannot be found") }
        ))
    }

    override fun deleteManufacturerById(id: Long): ResponseEntity<Long> {
        manufacturerRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateManufacturer(manufacturerDto: ManufacturerDto): ResponseEntity<ManufacturerDto> {
        val modifiedManufacturer: Manufacturer = manufacturerRepository.findById(manufacturerDto.id).stream()
                .map { manufacturer: Manufacturer ->
                    manufacturer.name = manufacturerDto.name
                    manufacturer
                }
                .findAny()
                .orElseThrow { NoSuchElementException("Manufacturer has not been found") }
        manufacturerRepository.save(modifiedManufacturer)
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.manufacturerToManufacturerDto(modifiedManufacturer))
    }
}