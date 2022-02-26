package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Category;
import ro.nicolaemariusghergu.queryit.repository.CategoryRepository;
import ro.nicolaemariusghergu.queryit.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public record CategoryServiceImpl(CategoryRepository categoryRepository) implements CategoryService {

    @NonNull
    @Override
    public Optional<Category> findById(@NonNull Long id) {
        return categoryRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRepository.saveAll(entities);
    }

    @NonNull
    @Override
    public void deleteById(@NonNull Long id) {
        categoryRepository.deleteById(id);
    }
}
