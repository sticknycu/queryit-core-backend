package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    ResponseEntity<CategoryDto> getCategoryById(@NonNull Long id);

    ResponseEntity<List<CategoryDto>> getCategories();

    ResponseEntity<CategoryDto> updateCategory(CategoryDto category);

    ResponseEntity<CategoryDto> getCategoryByName(String name);

    ResponseEntity<Long> addCategory(CategoryDto categoryDto);

    ResponseEntity<Long> deleteCategoryById(@NonNull Long id);
}
