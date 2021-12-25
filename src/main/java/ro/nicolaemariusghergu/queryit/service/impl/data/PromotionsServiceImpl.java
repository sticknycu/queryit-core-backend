package ro.nicolaemariusghergu.queryit.service.impl.data;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Promotions;
import ro.nicolaemariusghergu.queryit.repository.data.PromotionsRepository;
import ro.nicolaemariusghergu.queryit.service.data.PromotionsService;

import java.util.List;

@Service
public record PromotionsServiceImpl(PromotionsRepository promotionsRepository) implements PromotionsService {

    @Override
    public List<Promotions> findAll() {
        return promotionsRepository.findAll();
    }
}
