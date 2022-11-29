package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Truck
import java.util.*

@Repository
@Transactional
interface TruckRepository : JpaRepository<Truck?, Long?> {
    @NonNull
    override fun findById(@NonNull id: Long?): Optional<Truck?>?
    @NonNull
    override fun findAll(): MutableList<Truck?>?
    open fun findBySerialNumber(serialNumber: String?): Optional<Truck?>?
    @NonNull
    override fun <S : Truck?> save(@NonNull entity: S?): S?
    override fun <S : Truck?> saveAll(entities: Iterable<S?>?): MutableList<S?>?
}