package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.model.Promotion

@Mapper
interface PromotionMapper {
    open fun promotionToPromotionDto(promotion: Promotion?): PromotionDto?
    open fun promotionDtoToPromotion(promotionDto: PromotionDto?): Promotion?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(PromotionMapper::class.java)
    }
}