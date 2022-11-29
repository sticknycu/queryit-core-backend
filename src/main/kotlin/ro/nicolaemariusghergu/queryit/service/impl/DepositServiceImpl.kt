package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.DepositDto
import ro.nicolaemariusghergu.queryit.mapper.DepositMapper
import ro.nicolaemariusghergu.queryit.model.Deposit
import ro.nicolaemariusghergu.queryit.repository.DepositRepository
import ro.nicolaemariusghergu.queryit.service.DepositService

@Service
class DepositServiceImpl(private val depositRepository: DepositRepository) : DepositService {
    override fun findDepositById(id: Long): ResponseEntity<DepositDto> {
        return ResponseEntity.ok(depositRepository.findById(id).stream()
                .map { deposit: Deposit -> DepositMapper.INSTANCE.depositToDepositDto(deposit) }
                .findFirst()
                .orElseThrow { NoSuchElementException("Deposit does not exist!") }
        )
    }

    override fun getDeposits(): ResponseEntity<List<DepositDto>> {
        return ResponseEntity.ok(depositRepository.findAll()
                .stream()
                .map { deposit: Deposit -> DepositMapper.INSTANCE.depositToDepositDto(deposit) }
                .toList())
    }

    override fun addDeposit(depositDto: DepositDto): ResponseEntity<Long> {
        depositRepository.save(DepositMapper.INSTANCE.depositDtoToDeposit(depositDto))
        return ResponseEntity.ok(depositDto.id)
    }

    override fun getDepositByName(name: String): ResponseEntity<DepositDto> {
        return ResponseEntity.ok(DepositMapper.INSTANCE.depositToDepositDto(
                depositRepository.findByName(name)
                        .orElseThrow { NoSuchElementException("Deposit cannot be found") }
        ))
    }

    override fun deleteDepositById(id: Long): ResponseEntity<Long> {
        depositRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateDeposit(depositDto: DepositDto): ResponseEntity<DepositDto> {
        val modifiedDeposit: Deposit = depositRepository.findById(depositDto.id).stream()
                .map {deposit: Deposit ->
                    deposit.name = depositDto.name
                    deposit
                }
                .findAny()
                .orElseThrow { NoSuchElementException("Deposit has not been found") }
        depositRepository.save(modifiedDeposit)
        return ResponseEntity.ok(DepositMapper.INSTANCE.depositToDepositDto(modifiedDeposit))
    }
}