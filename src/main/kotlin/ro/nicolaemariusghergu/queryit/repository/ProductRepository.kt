package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Product
import java.util.*

@Transactional
interface ProductRepository : JpaRepository<Product, Long> {

    fun findByName(name: String): Optional<Product>

    fun findAllByCategoryId(categoryId: Long): MutableList<Product>
}