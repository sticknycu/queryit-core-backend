package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.model.Truck

@Mapper
interface TruckMapper {
    open fun truckToTruckDto(truck: Truck?): TruckDto?
    open fun truckDtoToTruck(truckDto: TruckDto?): Truck?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(TruckMapper::class.java)
    }
}