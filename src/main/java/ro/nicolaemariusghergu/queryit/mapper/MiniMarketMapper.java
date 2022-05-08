package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto;
import ro.nicolaemariusghergu.queryit.model.MiniMarket;

@Mapper
public interface MiniMarketMapper {

    MiniMarketMapper INSTANCE = Mappers.getMapper(MiniMarketMapper.class);

    MiniMarketDto miniMarketToMiniMarketDto(MiniMarket miniMarket);

    MiniMarket miniMarketDtoToMiniMarket(MiniMarketDto miniMarketDto);

    default String mapEmptyString(String string) {
        return string != null && !string.isEmpty() ? string : null;
    }
}
