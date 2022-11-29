package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Deposit
import java.util.*

@Transactional
interface DepositRepository : JpaRepository<Deposit, Long> {

    fun findByName(name: String): Optional<Deposit>
}