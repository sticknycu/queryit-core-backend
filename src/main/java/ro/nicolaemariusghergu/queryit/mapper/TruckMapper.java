package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.TruckDto;
import ro.nicolaemariusghergu.queryit.model.Truck;

@Mapper
public interface TruckMapper {

    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    TruckDto truckToTruckDto(Truck truck);

    Truck truckDtoToTruck(TruckDto truckDto);

    default String mapEmptyString(String string) {
        return string != null && !string.isEmpty() ? string : null;
    }
}
