package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.model.Promotion

@Mapper
interface PromotionMapper {
    fun promotionToPromotionDto(promotion: Promotion): PromotionDto
    fun promotionDtoToPromotion(promotionDto: PromotionDto): Promotion
    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE: PromotionMapper = Mappers.getMapper(PromotionMapper::class.java)
    }
}