package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto
import ro.nicolaemariusghergu.queryit.mapper.ManufacturerMapper
import ro.nicolaemariusghergu.queryit.model.Manufacturer
import ro.nicolaemariusghergu.queryit.service.ManufacturerService
import java.util.function.Function
import java.util.function.Supplier

@Service
class ManufacturerServiceImpl : ManufacturerService {
    override fun findManufacturerById(id: Long?): ResponseEntity<ManufacturerDto?>? {
        return ResponseEntity.ok(manufacturerRepository.findById(id).stream()
                .map<ManufacturerDto?>(Function<Manufacturer?, ManufacturerDto?> { manufacturer: Manufacturer? -> ManufacturerMapper.Companion.INSTANCE.manufacturerToManufacturerDto(manufacturer) })
                .findFirst()
                .orElseThrow<NoSuchElementException?>(
                        Supplier { NoSuchElementException("Manufacturer does not exist!") })
        )
    }

    override fun getManufacturers(): ResponseEntity<MutableList<ManufacturerDto?>?>? {
        return ResponseEntity.ok(manufacturerRepository.findAll()
                .stream()
                .map<ManufacturerDto?>(Function<Manufacturer?, ManufacturerDto?> { manufacturer: Manufacturer? -> ManufacturerMapper.Companion.INSTANCE.manufacturerToManufacturerDto(manufacturer) })
                .toList())
    }

    override fun addManufacturer(manufacturerDto: ManufacturerDto?): ResponseEntity<Long?>? {
        manufacturerRepository.save<Manufacturer?>(ManufacturerMapper.Companion.INSTANCE.manufacturerDtoToManufacturer(manufacturerDto))
        return ResponseEntity.ok(manufacturerDto.getId())
    }

    override fun getManufacturerByName(name: String?): ResponseEntity<ManufacturerDto?>? {
        return ResponseEntity.ok(ManufacturerMapper.Companion.INSTANCE.manufacturerToManufacturerDto(
                manufacturerRepository.findByName(name)
                        .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Manufacturer cannot be found") })
        ))
    }

    override fun deleteManufacturerById(id: Long?): ResponseEntity<Long?>? {
        manufacturerRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateManufacturer(manufacturerDto: ManufacturerDto?): ResponseEntity<ManufacturerDto?>? {
        val modifiedManufacturer: ManufacturerDto = manufacturerRepository.findById(manufacturerDto.getId()).stream()
                .map<ManufacturerDto?>(Function<Manufacturer?, ManufacturerDto?> { manufacturer: Manufacturer? -> ManufacturerMapper.Companion.INSTANCE.manufacturerToManufacturerDto(manufacturer) })
                .map<ManufacturerDto?>(Function { updatedManufacturer: ManufacturerDto? ->
                    updatedManufacturer.setName(manufacturerDto.getName())
                    updatedManufacturer
                })
                .findAny()
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Manufacturer has not been found") }
                )
        manufacturerRepository.save<Manufacturer?>(
                ManufacturerMapper.Companion.INSTANCE.manufacturerDtoToManufacturer(modifiedManufacturer))
        return ResponseEntity.ok(modifiedManufacturer)
    }
}