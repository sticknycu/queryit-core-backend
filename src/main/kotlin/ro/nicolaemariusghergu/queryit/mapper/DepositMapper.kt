package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.DepositDto
import ro.nicolaemariusghergu.queryit.model.Deposit

@Mapper
interface DepositMapper {
    fun depositToDepositDto(deposit: Deposit): DepositDto

    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE: DepositMapper = Mappers.getMapper(DepositMapper::class.java)
    }
}