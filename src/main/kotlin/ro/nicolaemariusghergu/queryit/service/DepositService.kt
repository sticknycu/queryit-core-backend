package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.DepositDto


interface DepositService {
    fun findDepositById(id: Long): ResponseEntity<DepositDto>

    fun getDeposits(): ResponseEntity<List<DepositDto>>

    fun addDeposit(depositDto: DepositDto): ResponseEntity<Long>

    fun getDepositByName(name: String): ResponseEntity<DepositDto>

    fun deleteDepositById(id: Long): ResponseEntity<Long>

    fun updateDeposit(depositDto: DepositDto): ResponseEntity<DepositDto>
}