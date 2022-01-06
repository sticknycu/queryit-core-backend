package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Deposit;
import ro.nicolaemariusghergu.queryit.repository.DepositRepository;
import ro.nicolaemariusghergu.queryit.service.DepositService;

import java.util.List;
import java.util.Optional;

@Service
public record DepositServiceImpl(DepositRepository depositRepository) implements DepositService {

    @NonNull
    @Override
    public Optional<Deposit> findById(@NonNull Long id) {
        return depositRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findByName(String name) {
        return depositRepository.findByName(name);
    }
}
