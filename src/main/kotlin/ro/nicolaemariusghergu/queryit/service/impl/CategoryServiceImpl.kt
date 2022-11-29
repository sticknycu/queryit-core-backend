package ro.nicolaemariusghergu.queryit.service.impl

import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper
import ro.nicolaemariusghergu.queryit.model.*
import ro.nicolaemariusghergu.queryit.service.CategoryService
import java.util.function.Function
import java.util.function.Supplier

@Slf4j
@Service
class CategoryServiceImpl : CategoryService {
    @NonNull
    override fun getCategoryById(@NonNull id: Long?): ResponseEntity<CategoryDto?>? {
        return ResponseEntity.ok(CategoryMapper.Companion.INSTANCE
                .categoryToCategoryDto(categoryRepository.findById(id)
                        .stream()
                        .findAny()
                        .orElseThrow<NoSuchElementException?>(
                                Supplier { NoSuchElementException("Categoria nu a putut fi gasita") }
                        )
                ))
    }

    override fun getCategories(): ResponseEntity<MutableList<CategoryDto?>?>? {
        return ResponseEntity.ok(categoryRepository.findAll().stream()
                .map<CategoryDto?>(Function<Category?, CategoryDto?> { category: Category? -> CategoryMapper.Companion.INSTANCE.categoryToCategoryDto(category) })
                .toList())
    }

    override fun updateCategory(category: CategoryDto?): ResponseEntity<CategoryDto?>? {
        val categoryDto: CategoryDto = categoryRepository.findById(category.getId())
                .map<CategoryDto?>(Function<Category?, CategoryDto?> { category: Category? -> CategoryMapper.Companion.INSTANCE.categoryToCategoryDto(category) })
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Category has not been found") })
        addCategory(categoryDto)
        return ResponseEntity.ok(categoryDto)
    }

    override fun getCategoryByName(name: String?): ResponseEntity<CategoryDto?>? {
        return ResponseEntity.ok(CategoryMapper.Companion.INSTANCE
                .categoryToCategoryDto(categoryRepository.findByName(name).stream()
                        .findAny()
                        .orElseThrow<NoSuchElementException?>(
                                Supplier { NoSuchElementException("Category has not been found") })
                ))
    }

    override fun addCategory(categoryDto: CategoryDto?): ResponseEntity<Long?>? {
        categoryRepository.save<Category?>(CategoryMapper.Companion.INSTANCE
                .categoryDtoToCategory(categoryDto))
        return ResponseEntity.ok(categoryDto.getId())
    }

    @NonNull
    override fun deleteCategoryById(@NonNull id: Long?): ResponseEntity<Long?>? {
        categoryRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }
}