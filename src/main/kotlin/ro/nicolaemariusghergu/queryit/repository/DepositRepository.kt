package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Deposit
import java.util.*

@Repository
@Transactional
interface DepositRepository : JpaRepository<Deposit?, Long?> {
    @NonNull
    override fun findById(@NonNull id: Long?): Optional<Deposit?>?
    @NonNull
    override fun findAll(): MutableList<Deposit?>?
    open fun findByName(name: String?): Optional<Deposit?>?
    override fun <S : Deposit?> save(entity: S?): S?
    override fun <S : Deposit?> saveAll(entities: Iterable<S?>?): MutableList<S?>?
}