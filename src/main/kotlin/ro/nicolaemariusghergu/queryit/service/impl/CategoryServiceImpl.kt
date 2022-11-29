package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper
import ro.nicolaemariusghergu.queryit.model.Category
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository
import ro.nicolaemariusghergu.queryit.service.CategoryService

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {
    @NonNull
    override fun getCategoryById(@NonNull id: Long): ResponseEntity<CategoryDto> {
        return ResponseEntity.ok(CategoryMapper.INSTANCE
                .categoryToCategoryDto(categoryRepository.findById(id)
                        .stream()
                        .findAny()
                        .orElseThrow { NoSuchElementException("Categoria nu a putut fi gasita") }
                ))
    }

    override fun getCategories(): ResponseEntity<List<CategoryDto>> {
        return ResponseEntity.ok(categoryRepository.findAll().stream()
                .map { category: Category -> CategoryMapper.INSTANCE.categoryToCategoryDto(category) }
                .toList())
    }

    override fun updateCategory(category: CategoryDto): ResponseEntity<CategoryDto> {
        val categoryDto: CategoryDto = categoryRepository.findById(category.id)
                .map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .orElseThrow { NoSuchElementException("Category has not been found") } as CategoryDto
        addCategory(categoryDto)
        return ResponseEntity.ok(categoryDto)
    }

    override fun getCategoryByName(name: String): ResponseEntity<CategoryDto> {
        return ResponseEntity.ok(CategoryMapper.INSTANCE
                .categoryToCategoryDto(categoryRepository.findByName(name).stream()
                        .findAny()
                        .orElseThrow { NoSuchElementException("Category has not been found") }
                ))
    }

    override fun addCategory(categoryDto: CategoryDto): ResponseEntity<Long> {
        categoryRepository.save(CategoryMapper.INSTANCE
                .categoryDtoToCategory(categoryDto))
        return ResponseEntity.ok(categoryDto.id)
    }

    @NonNull
    override fun deleteCategoryById(@NonNull id: Long): ResponseEntity<Long> {
        categoryRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }
}