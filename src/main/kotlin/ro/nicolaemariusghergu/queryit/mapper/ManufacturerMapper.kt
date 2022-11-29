package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto
import ro.nicolaemariusghergu.queryit.model.Manufacturer

@Mapper
interface ManufacturerMapper {
    fun manufacturerToManufacturerDto(manufacturer: Manufacturer): ManufacturerDto
    fun manufacturerDtoToManufacturer(manufacturerDto: ManufacturerDto): Manufacturer

    companion object {
        val INSTANCE: ManufacturerMapper = Mappers.getMapper(ManufacturerMapper::class.java)
    }
}