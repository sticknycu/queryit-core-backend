package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;
import ro.nicolaemariusghergu.queryit.repository.data.ShelfCategoryRepository;
import ro.nicolaemariusghergu.queryit.service.data.ShelfCategoryService;

import java.util.List;
import java.util.Optional;

@Service
public record ShelfCategoryServiceImpl(
        ShelfCategoryRepository shelfCategoryRepository) implements ShelfCategoryService {

    @Override
    public Optional<ShelfCategory> findById(Long id) {
        return shelfCategoryRepository.findById(id);
    }

    @Override
    public Optional<ShelfCategory> findByName(String name) {
        return shelfCategoryRepository.findByName(name);
    }

    @Override
    public List<ShelfCategory> findAll() {
        return shelfCategoryRepository.findAll();
    }
}
