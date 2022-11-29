package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.model.Truck

@Mapper
interface TruckMapper {
    fun truckToTruckDto(truck: Truck): TruckDto
    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE: TruckMapper = Mappers.getMapper(TruckMapper::class.java)
    }
}