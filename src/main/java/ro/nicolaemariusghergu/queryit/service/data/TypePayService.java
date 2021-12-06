package ro.nicolaemariusghergu.queryit.service.data;

import ro.nicolaemariusghergu.queryit.model.data.TypePay;

import java.util.Optional;

public interface TypePayService {

    Optional<TypePay> findById(Long id);

    Optional<TypePay> findByType(String type);
}
