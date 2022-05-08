package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.CategoryDto;
import ro.nicolaemariusghergu.queryit.service.CategoryService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@RequestMapping("/api/categories")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
@Controller
public record CategoryController(CategoryService categoryService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/v1/{categoryId}")
    @ResponseBody
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/v1/category-name")
    @ResponseBody
    public ResponseEntity<CategoryDto> getCategoryByName(@RequestBody CategoryDto categoryDto) {
        return categoryService.getCategoryByName(categoryDto.getName());
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping("/v1/{categoryId}")
    @ResponseBody
    public ResponseEntity<Long> deleteProduct(@PathVariable Long categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }
}
