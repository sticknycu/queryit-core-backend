package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.MiniMarket;
import ro.nicolaemariusghergu.queryit.repository.MiniMarketRepository;
import ro.nicolaemariusghergu.queryit.service.MiniMarketService;

import java.util.List;
import java.util.Optional;

@Service
public record MiniMarketServiceImpl(MiniMarketRepository miniMarketRepository) implements MiniMarketService {

    @NonNull
    @Override
    public Optional<MiniMarket> findById(@NonNull Long id) {
        return miniMarketRepository.findById(id);
    }

    @NonNull
    @Override
    public List<MiniMarket> findAll() {
        return miniMarketRepository.findAll();
    }

    @Override
    public Optional<MiniMarket> findByName(String name) {
        return miniMarketRepository.findByName(name);
    }

    @Override
    public <S extends MiniMarket> S save(S entity) {
        return miniMarketRepository.save(entity);
    }

    @Override
    public <S extends MiniMarket> List<S> saveAll(Iterable<S> entities) {
        return miniMarketRepository.saveAll(entities);
    }
}
