package ro.nicolaemariusghergu.queryit.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;

import java.util.Optional;

@Repository
public interface TypePayRepository extends JpaRepository<TypePay, Long> {

    @NonNull
    @Override
    Optional<TypePay> findById(@NonNull Long id);

    Optional<TypePay> findByType(String type);
}
