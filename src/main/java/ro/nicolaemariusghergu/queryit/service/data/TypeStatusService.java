package ro.nicolaemariusghergu.queryit.service.data;

import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;

import java.util.Optional;

public interface TypeStatusService {

    Optional<TypeStatus> findById(Long id);

    Optional<TypeStatus> findByType(String type);
}
