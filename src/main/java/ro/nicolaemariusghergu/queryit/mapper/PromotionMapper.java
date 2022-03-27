package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.model.Promotion;

@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    PromotionDto promotionToPromotionDto(Promotion promotion);

    Promotion promotionDtoToPromotion(PromotionDto promotionDto);
}
