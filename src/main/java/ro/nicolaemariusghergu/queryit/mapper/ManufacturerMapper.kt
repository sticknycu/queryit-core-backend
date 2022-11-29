package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto
import ro.nicolaemariusghergu.queryit.model.Manufacturer

@Mapper
interface ManufacturerMapper {
    open fun manufacturerToManufacturerDto(manufacturer: Manufacturer?): ManufacturerDto?
    open fun manufacturerDtoToManufacturer(manufacturerDto: ManufacturerDto?): Manufacturer?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(ManufacturerMapper::class.java)
    }
}