package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.MinishopCategory;
import ro.nicolaemariusghergu.queryit.repository.data.MinishopCategoryRepository;
import ro.nicolaemariusghergu.queryit.service.data.MinishopCategoryService;

import java.util.List;
import java.util.Optional;

@Service
public record MinishopCategoryServiceImpl(
        MinishopCategoryRepository minishopCategoryRepository) implements MinishopCategoryService {

    @Override
    public MinishopCategory getById(Long id) {
        return minishopCategoryRepository.getById(id);
    }

    @Override
    public Optional<MinishopCategory> getByName(String name) {
        return minishopCategoryRepository.getByName(name);
    }

    @Override
    public List<MinishopCategory> findAll() {
        return minishopCategoryRepository.findAll();
    }
}
