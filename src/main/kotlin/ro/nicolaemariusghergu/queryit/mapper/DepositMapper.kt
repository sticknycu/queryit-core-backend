package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.DepositDto
import ro.nicolaemariusghergu.queryit.model.Deposit

@Mapper
interface DepositMapper {
    open fun depositToDepositDto(deposit: Deposit?): DepositDto?
    open fun depositDtoToDeposit(depositDto: DepositDto?): Deposit?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(DepositMapper::class.java)
    }
}