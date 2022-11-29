package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.model.Truck

@Mapper
interface TruckMapper {
    fun truckToTruckDto(truck: Truck): TruckDto
    fun toDto(truckDto: TruckDto): Truck

    companion object {
        val INSTANCE: TruckMapper = Mappers.getMapper(TruckMapper::class.java)
    }
}