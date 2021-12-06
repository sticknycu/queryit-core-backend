package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.repository.data.TypePayRepository;
import ro.nicolaemariusghergu.queryit.service.data.TypePayService;

import java.util.Optional;

@Service
public record TypePayServiceImpl(TypePayRepository typePayRepository) implements TypePayService {

    @Override
    public Optional<TypePay> findById(Long id) {
        return typePayRepository.findById(id);
    }

    @Override
    public Optional<TypePay> findByType(String type) {
        return typePayRepository.findByType(type);
    }
}
