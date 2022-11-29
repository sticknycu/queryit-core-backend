package ro.nicolaemariusghergu.queryit.service.impl

import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper
import ro.nicolaemariusghergu.queryit.model.*
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository
import ro.nicolaemariusghergu.queryit.service.CategoryService
import java.util.function.Function
import java.util.function.Supplier

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override fun getCategories(): ResponseEntity<MutableList<CategoryDto>> {
        return ResponseEntity.ok(categoryRepository.findAll().stream()
                .map { category: Category -> CategoryMapper.INSTANCE.categoryToCategoryDto(category) }
                .toList())
    }

    override fun addCategory(categoryDto: CategoryDto): ResponseEntity<Long> {
        categoryRepository.save(CategoryMapper.INSTANCE
                .categoryDtoToCategory(categoryDto))
        return ResponseEntity.ok(categoryDto.id)
    }
}