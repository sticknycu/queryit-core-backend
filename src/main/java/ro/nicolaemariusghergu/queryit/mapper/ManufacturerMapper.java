package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.ManufacturerDto;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;

@Mapper
public interface ManufacturerMapper {

    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

    ManufacturerDto manufacturerToManufacturerDto(Manufacturer manufacturer);

    Manufacturer manufacturerDtoToManufacturer(ManufacturerDto manufacturerDto);

    default String mapEmptyString(String string) {
        return string != null && !string.isEmpty() ? string : null;
    }
}

