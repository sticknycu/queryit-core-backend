package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto
import ro.nicolaemariusghergu.queryit.model.MiniMarket

@Mapper
interface MiniMarketMapper {
    fun miniMarketToMiniMarketDto(miniMarket: MiniMarket): MiniMarketDto
    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE: MiniMarketMapper = Mappers.getMapper(MiniMarketMapper::class.java)
    }
}