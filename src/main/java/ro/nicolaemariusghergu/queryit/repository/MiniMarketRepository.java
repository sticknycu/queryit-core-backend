package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.MiniMarket;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiniMarketRepository extends JpaRepository<MiniMarket, Long> {

    @NonNull
    @Override
    Optional<MiniMarket> findById(@NonNull Long id);

    @NonNull
    @Override
    List<MiniMarket> findAll();

    Optional<MiniMarket> findByName(String name);

    @Override
    <S extends MiniMarket> S save(S entity);

    @Override
    <S extends MiniMarket> List<S> saveAll(Iterable<S> entities);
}
