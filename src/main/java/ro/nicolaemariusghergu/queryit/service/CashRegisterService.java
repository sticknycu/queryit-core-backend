package ro.nicolaemariusghergu.queryit.service;

import ro.nicolaemariusghergu.queryit.model.CashRegister;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;

import java.util.Optional;

public interface CashRegisterService {

    Optional<CashRegister> findById(Long id);

    Optional<CashRegister> findByNumber(Integer number);

    Optional<CashRegister> findCasaMarcatByTypeStatus(TypeStatus typeStatus);

    Optional<CashRegister> findCasaMarcatByTypePay(TypePay typePay);

    Optional<CashRegister> findCasaMarcatByTypeStatusAndTypePay(TypeStatus typeStatus, TypePay typePay);
}
