package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Product
import java.util.*

@Repository
@Transactional
interface ProductRepository : JpaRepository<Product?, Long?> {
    open fun findAllByCategoryId(id: Long?): MutableList<Product?>?
    open fun findByName(name: String?): Optional<Product?>?
    open fun findAllByPromotionId(id: Long?): MutableList<Product?>?
}