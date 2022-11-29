package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Manufacturer
import java.util.*

@Transactional
interface ManufacturerRepository : JpaRepository<Manufacturer, Long> {
    fun findByName(name: String?): Optional<Manufacturer>
}