package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import ro.nicolaemariusghergu.queryit.dto.CategoryDto

interface CategoryService {
    open fun getCategoryById(@NonNull id: Long?): ResponseEntity<CategoryDto?>?
    open fun getCategories(): ResponseEntity<MutableList<CategoryDto?>?>?
    open fun updateCategory(category: CategoryDto?): ResponseEntity<CategoryDto?>?
    open fun getCategoryByName(name: String?): ResponseEntity<CategoryDto?>?
    open fun addCategory(categoryDto: CategoryDto?): ResponseEntity<Long?>?
    open fun deleteCategoryById(@NonNull id: Long?): ResponseEntity<Long?>?
}