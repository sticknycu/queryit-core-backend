package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.service.CategoryService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:60028/v1/products/")
@Controller
public record CategoryController(CategoryService categoryService) {

    @GetMapping("categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    @ResponseBody
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryService.findById(categoryId), HttpStatus.OK);
    }

    @PostMapping("/categories")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping("/categories/{categoryId}")
    @ResponseBody
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        Optional<Category> optionalCategory = categoryService.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category oldCategory = optionalCategory.get();
            oldCategory.setName(category.getName());

            categoryService.save(oldCategory);

            return new ResponseEntity<>(oldCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    @ResponseBody
    public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
