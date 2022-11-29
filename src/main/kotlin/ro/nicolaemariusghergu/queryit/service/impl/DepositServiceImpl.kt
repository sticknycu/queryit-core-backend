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
    override fun getDeposits(): ResponseEntity<MutableList<DepositDto>> {
        return ResponseEntity.ok(depositRepository.findAll()
                .stream()
                .map { deposit: Deposit -> DepositMapper.INSTANCE.depositToDepositDto(deposit) }
                .toList())
    }
}