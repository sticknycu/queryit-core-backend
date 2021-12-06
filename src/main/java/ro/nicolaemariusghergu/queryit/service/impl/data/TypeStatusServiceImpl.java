package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.repository.data.TypeStatusRepository;
import ro.nicolaemariusghergu.queryit.service.data.TypeStatusService;

import java.util.Optional;

@Service
public record TypeStatusServiceImpl(TypeStatusRepository typeStatusRepository) implements TypeStatusService {

    @Override
    public Optional<TypeStatus> findById(Long id) {
        return typeStatusRepository.findById(id);
    }

    @Override
    public Optional<TypeStatus> findByType(String type) {
        return typeStatusRepository.findByType(type);
    }
}

