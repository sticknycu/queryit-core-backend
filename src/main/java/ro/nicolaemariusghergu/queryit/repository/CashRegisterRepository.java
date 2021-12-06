package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.CashRegister;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;

import java.util.Optional;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {

    @NonNull
    @Override
    Optional<CashRegister> findById(@NonNull Long id);

    Optional<CashRegister> findByNumber(Integer number);

    Optional<CashRegister> findCasaMarcatByTypeStatus(TypeStatus typeStatus);

    Optional<CashRegister> findCasaMarcatByTypePay(TypePay typePay);

    Optional<CashRegister> findCasaMarcatByTypeStatusAndTypePay(TypeStatus typeStatus, TypePay typePay);
}
