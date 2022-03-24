package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.service.CategoryService;

import java.util.List;
import java.util.Optional;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCAL_HOST_ADDRESS;

@CrossOrigin(LOCAL_HOST_ADDRESS)
@Controller
public record CategoryController(CategoryService categoryService) {

    @GetMapping("/v1/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/v1/categories/{categoryId}")
    @ResponseBody
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryService.findById(categoryId), HttpStatus.OK);
    }

    @PostMapping("/v1/categories")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping("/v1/categories/{categoryId}")
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

    @DeleteMapping("/v1/categories/{categoryId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
