package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.service.CategoryService

@RequestMapping("/api/categories")
@Controller
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/v1")
    @ResponseBody
    fun getCategories(): ResponseEntity<List<CategoryDto>> {
        return categoryService.getCategories()
    }

    @GetMapping("/v1/{categoryId}")
    @ResponseBody
    fun getCategoryById(@PathVariable categoryId: Long): ResponseEntity<CategoryDto> {
        return categoryService.getCategoryById(categoryId)
    }

    @GetMapping("/v1/category-name")
    @ResponseBody
    fun getCategoryByName(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        return categoryService.getCategoryByName(categoryDto.name)
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<Long> {
        return categoryService.addCategory(categoryDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        return categoryService.updateCategory(categoryDto)
    }

    @DeleteMapping("/v1/{categoryId}")
    @ResponseBody
    fun deleteProduct(@PathVariable categoryId: Long): ResponseEntity<Long> {
        return categoryService.deleteCategoryById(categoryId)
    }
}