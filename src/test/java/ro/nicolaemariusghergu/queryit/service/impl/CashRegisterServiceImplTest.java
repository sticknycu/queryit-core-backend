package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.queryit.exceptions.ResourceNotFoundException;
import ro.nicolaemariusghergu.queryit.model.CashRegister;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.service.CashRegisterService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CashRegisterServiceImplTest {

    private static final Long ID = 1L;
    private static final Integer NUMBER = 2;

    @Autowired
    CashRegisterService cashRegisterService;

    @Test
    void whenFindByIdthenReturnCasaMarcat() {
        // given
        CashRegister cashRegister = new CashRegister();
        cashRegister.setId(ID);

        // when - Without Mock
        Optional<CashRegister> found = cashRegisterService.findById(cashRegister.getId());

        // then
        found.ifPresentOrElse(casaMarcatFounded -> {
            assertNotNull(casaMarcatFounded.getNumber());
            assertNotNull(casaMarcatFounded.getLocalMoney());
            assertNotNull(casaMarcatFounded.getTypePay());
            assertNotNull(casaMarcatFounded.getTypeStatus());
        }, () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByNumberthenReturnCasaMarcat() {
        // given
        CashRegister cashRegister = new CashRegister();
        cashRegister.setNumber(NUMBER);

        // when - Without Mock
        Optional<CashRegister> found = cashRegisterService.findByNumber(cashRegister.getNumber());

        // then
        found.ifPresentOrElse(casaMarcatFounded -> {
            assertNotNull(casaMarcatFounded.getNumber());
            assertNotNull(casaMarcatFounded.getLocalMoney());
            assertNotNull(casaMarcatFounded.getTypePay());
            assertNotNull(casaMarcatFounded.getTypeStatus());
        }, () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByTypeStatusAndTypePayExiststhenReturnCasaMarcat() {
        TypeStatus typeStatus = new TypeStatus();
        typeStatus.setId(ID);

        TypePay typePay = new TypePay();
        typePay.setId(ID);

        // given
        CashRegister cashRegister = new CashRegister();
        cashRegister.setTypeStatus(typeStatus);
        cashRegister.setTypePay(typePay);

        // when - Without Mock
        Optional<CashRegister> found = cashRegisterService.findCasaMarcatByTypeStatusAndTypePay(cashRegister.getTypeStatus(), typePay);

        // then
        found.ifPresentOrElse(casaMarcatFounded -> {
            assertNotNull(casaMarcatFounded.getNumber());
            assertNotNull(casaMarcatFounded.getLocalMoney());
            assertNotNull(casaMarcatFounded.getTypePay());
            assertNotNull(casaMarcatFounded.getTypeStatus());
        }, () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }
}
