package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Deposit;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @NonNull
    @Override
    Optional<Deposit> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Deposit> findAll();

    Optional<Deposit> findByName(String name);

    @Override
    <S extends Deposit> S save(S entity);

    @Override
    <S extends Deposit> List<S> saveAll(Iterable<S> entities);
}
