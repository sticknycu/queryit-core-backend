package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.DepositDto
import ro.nicolaemariusghergu.queryit.model.Deposit

@Mapper
interface DepositMapper {
    fun depositToDepositDto(deposit: Deposit): DepositDto
    fun depositDtoToDeposit(depositDto: DepositDto): Deposit

    companion object {
        val INSTANCE: DepositMapper = Mappers.getMapper(DepositMapper::class.java)
    }
}