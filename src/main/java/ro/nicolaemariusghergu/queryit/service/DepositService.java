package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Deposit;

import java.util.List;
import java.util.Optional;

public interface DepositService {

    @NonNull
    Optional<Deposit> findById(@NonNull Long id);

    @NonNull
    List<Deposit> findAll();

    Optional<Deposit> findByName(String name);

    <S extends Deposit> S save(S entity);

    <S extends Deposit> List<S> saveAll(Iterable<S> entities);
}
