package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Minishop;
import ro.nicolaemariusghergu.queryit.model.data.MinishopCategory;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.repository.MinishopRepository;
import ro.nicolaemariusghergu.queryit.service.MinishopService;

import java.util.List;
import java.util.Optional;

@Service
public record MinishopServiceImpl(MinishopRepository minishopRepository) implements MinishopService {

    @Override
    public Optional<Minishop> findById(Long id) {
        return minishopRepository.findById(id);
    }

    @Override
    public List<Minishop> findAllByMinishopCategory(MinishopCategory minishopCategory) {
        return minishopRepository.findAllByMinishopCategory(minishopCategory);
    }

    @Override
    public List<Minishop> findAllByProduct(Product product) {
        return minishopRepository.findAllByProduct(product);
    }
}
