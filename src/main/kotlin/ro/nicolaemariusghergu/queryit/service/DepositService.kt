package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.DepositDto

interface DepositService {
    open fun findDepositById(id: Long?): ResponseEntity<DepositDto?>?
    open fun getDeposits(): ResponseEntity<MutableList<DepositDto?>?>?
    open fun addDeposit(depositDto: DepositDto?): ResponseEntity<Long?>?
    open fun getDepositByName(name: String?): ResponseEntity<DepositDto?>?
    open fun deleteDepositById(id: Long?): ResponseEntity<Long?>?
    open fun updateDeposit(depositDto: DepositDto?): ResponseEntity<DepositDto?>?
}