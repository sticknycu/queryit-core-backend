package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto
import ro.nicolaemariusghergu.queryit.model.MiniMarket

@Mapper
interface MiniMarketMapper {
    open fun miniMarketToMiniMarketDto(miniMarket: MiniMarket?): MiniMarketDto?
    open fun miniMarketDtoToMiniMarket(miniMarketDto: MiniMarketDto?): MiniMarket?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(MiniMarketMapper::class.java)
    }
}