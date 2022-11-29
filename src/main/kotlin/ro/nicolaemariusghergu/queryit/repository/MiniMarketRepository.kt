package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.MiniMarket
import java.util.*

@Transactional
interface MiniMarketRepository : JpaRepository<MiniMarket, Long> {

    fun findByName(name: String): Optional<MiniMarket>
}