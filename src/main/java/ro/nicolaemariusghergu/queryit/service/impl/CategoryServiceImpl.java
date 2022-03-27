package ro.nicolaemariusghergu.queryit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.CategoryDto;
import ro.nicolaemariusghergu.queryit.mapper.CategoryMapper;
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository;
import ro.nicolaemariusghergu.queryit.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public record CategoryServiceImpl(CategoryRepository categoryRepository) implements CategoryService {

    @NonNull
    @Override
    public ResponseEntity<CategoryDto> getCategoryById(@NonNull Long id) {
        return ResponseEntity.ok(CategoryMapper.INSTANCE
                .categoryToCategoryDto(categoryRepository.findById(id)
                        .stream()
                        .findAny()
                        .orElseThrow(
                                () -> new NoSuchElementException("Categoria nu a putut fi gasita")
                        )
                ));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll().stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .toList());
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(CategoryDto category) {
        CategoryDto categoryDto = categoryRepository.findById(category.getId())
                .map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .orElseThrow(() ->
                        new NoSuchElementException("Category has not been found"));
        addCategory(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @Override
    public ResponseEntity<CategoryDto> getCategoryByName(String name) {
        return ResponseEntity.ok(CategoryMapper.INSTANCE
                .categoryToCategoryDto(categoryRepository.findByName(name).stream()
                        .findAny()
                        .orElseThrow(
                                () -> new NoSuchElementException("Category has not been found"))
                ));
    }

    @Override
    public ResponseEntity<Long> addCategory(CategoryDto categoryDto) {
        categoryRepository.save(CategoryMapper.INSTANCE
                .categoryDtoToCategory(categoryDto));
        return ResponseEntity.ok(categoryDto.getId());
    }

    @NonNull
    @Override
    public ResponseEntity<Long> deleteCategoryById(@NonNull Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
