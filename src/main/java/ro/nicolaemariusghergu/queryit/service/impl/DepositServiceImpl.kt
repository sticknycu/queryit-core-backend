package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.DepositDto
import ro.nicolaemariusghergu.queryit.mapper.DepositMapper
import ro.nicolaemariusghergu.queryit.model.Deposit
import ro.nicolaemariusghergu.queryit.service.DepositService
import java.util.function.Function
import java.util.function.Supplier

@Service
class DepositServiceImpl : DepositService {
    override fun findDepositById(id: Long?): ResponseEntity<DepositDto?>? {
        return ResponseEntity.ok(depositRepository.findById(id).stream()
                .map<DepositDto?>(Function<Deposit?, DepositDto?> { deposit: Deposit? -> DepositMapper.Companion.INSTANCE.depositToDepositDto(deposit) })
                .findFirst()
                .orElseThrow<NoSuchElementException?>(
                        Supplier { NoSuchElementException("Deposit does not exist!") })
        )
    }

    override fun getDeposits(): ResponseEntity<MutableList<DepositDto?>?>? {
        return ResponseEntity.ok(depositRepository.findAll()
                .stream()
                .map<DepositDto?>(Function<Deposit?, DepositDto?> { deposit: Deposit? -> DepositMapper.Companion.INSTANCE.depositToDepositDto(deposit) })
                .toList())
    }

    override fun addDeposit(depositDto: DepositDto?): ResponseEntity<Long?>? {
        depositRepository.save<Deposit?>(DepositMapper.Companion.INSTANCE.depositDtoToDeposit(depositDto))
        return ResponseEntity.ok(depositDto.getId())
    }

    override fun getDepositByName(name: String?): ResponseEntity<DepositDto?>? {
        return ResponseEntity.ok(DepositMapper.Companion.INSTANCE.depositToDepositDto(
                depositRepository.findByName(name)
                        .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Deposit cannot be found") })
        ))
    }

    override fun deleteDepositById(id: Long?): ResponseEntity<Long?>? {
        depositRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateDeposit(depositDto: DepositDto?): ResponseEntity<DepositDto?>? {
        val modifiedDeposit: DepositDto = depositRepository.findById(depositDto.getId()).stream()
                .map<DepositDto?>(Function<Deposit?, DepositDto?> { deposit: Deposit? -> DepositMapper.Companion.INSTANCE.depositToDepositDto(deposit) })
                .map<DepositDto?>(Function { updatedDeposit: DepositDto? ->
                    updatedDeposit.setName(depositDto.getName())
                    updatedDeposit
                })
                .findAny()
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Deposit has not been found") }
                )
        depositRepository.save<Deposit?>(
                DepositMapper.Companion.INSTANCE.depositDtoToDeposit(modifiedDeposit))
        return ResponseEntity.ok(modifiedDeposit)
    }
}