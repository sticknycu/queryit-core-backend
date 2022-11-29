package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import ro.nicolaemariusghergu.queryit.dto.CategoryDto


interface CategoryService {
    fun getCategoryById(@NonNull id: Long): ResponseEntity<CategoryDto>

    fun getCategories(): ResponseEntity<List<CategoryDto>>

    fun updateCategory(category: CategoryDto): ResponseEntity<CategoryDto>

    fun getCategoryByName(name: String): ResponseEntity<CategoryDto>

    fun addCategory(categoryDto: CategoryDto): ResponseEntity<Long>

    fun deleteCategoryById(@NonNull id: Long): ResponseEntity<Long>
}