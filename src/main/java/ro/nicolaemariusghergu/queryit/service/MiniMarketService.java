package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.MiniMarket;

import java.util.List;
import java.util.Optional;

public interface MiniMarketService {

    @NonNull
    Optional<MiniMarket> findById(@NonNull Long id);

    @NonNull
    List<MiniMarket> findAll();

    Optional<MiniMarket> findByName(String name);

    <S extends MiniMarket> S save(S entity);

    <S extends MiniMarket> List<S> saveAll(Iterable<S> entities);
}
