package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.model.Promotion;

@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "expireDate", target = "expireDate")
    @Mapping(source = "quantityNeeded", target = "quantityNeeded")
    PromotionDto promotionToPromotionDto(Promotion promotion);
}
