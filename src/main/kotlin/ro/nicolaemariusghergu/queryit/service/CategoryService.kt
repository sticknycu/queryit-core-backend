package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.CategoryDto

interface CategoryService {
    fun getCategories(): ResponseEntity<MutableList<CategoryDto>>
    fun addCategory(categoryDto: CategoryDto): ResponseEntity<Long>
}