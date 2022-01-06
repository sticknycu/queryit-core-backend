package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Promotion;
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;
import java.util.Optional;

@Service
public record PromotionServiceImpl(PromotionRepository promotionRepository) implements PromotionService {

    @NonNull
    @Override
    public Optional<Promotion> findById(@NonNull Long id) {
        return promotionRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> findByName(String name) {
        return promotionRepository.findByName(name);
    }
}
