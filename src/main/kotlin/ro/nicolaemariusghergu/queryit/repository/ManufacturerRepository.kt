package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Manufacturer
import java.util.*

@Repository
@Transactional
interface ManufacturerRepository : JpaRepository<Manufacturer?, Long?> {
    @NonNull
    override fun findById(@NonNull id: Long?): Optional<Manufacturer?>?
    @NonNull
    override fun findAll(): MutableList<Manufacturer?>?
    open fun findByName(name: String?): Optional<Manufacturer?>?
    @NonNull
    override fun <S : Manufacturer?> save(@NonNull entity: S?): S?
    @NonNull
    override fun <S : Manufacturer?> saveAll(@NonNull entities: Iterable<S?>?): MutableList<S?>?
}