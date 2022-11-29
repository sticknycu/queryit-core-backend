package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.MiniMarket
import java.util.*

@Repository
@Transactional
interface MiniMarketRepository : JpaRepository<MiniMarket?, Long?> {
    @NonNull
    override fun findById(@NonNull id: Long?): Optional<MiniMarket?>?
    @NonNull
    override fun findAll(): MutableList<MiniMarket?>?
    open fun findByName(name: String?): Optional<MiniMarket?>?
    override fun <S : MiniMarket?> save(entity: S?): S?
    override fun <S : MiniMarket?> saveAll(entities: Iterable<S?>?): MutableList<S?>?
}