package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.DepositDto

interface DepositService {
    fun getDeposits(): ResponseEntity<MutableList<DepositDto>>
}