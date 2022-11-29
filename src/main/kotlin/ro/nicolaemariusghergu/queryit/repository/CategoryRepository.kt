package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Category
import java.util.*

@Repository
@Transactional
interface CategoryRepository : JpaRepository<Category?, Long?> {
    open fun findByName(name: String?): Optional<Category?>?
}