package ro.nicolaemariusghergu.queryit.service.impl;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ro.nicolaemariusghergu.queryit.exceptions.ResourceNotFoundException;
import ro.nicolaemariusghergu.queryit.model.CashRegister;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.repository.CashRegisterRepository;
import ro.nicolaemariusghergu.queryit.service.CashRegisterService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CashRegisterServiceImplTest {

    private static final Long ID = 1L;
    private static final Integer NUMBER = 2;

    @Autowired
    private CashRegisterService cashRegisterService;

    @MockBean
    private CashRegisterRepository cashRegisterRepository;

    @BeforeEach
    void configurateBeans() {
        EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().collectionSizeRange(1, 2));

        CashRegister cashRegister = new CashRegister();
        cashRegister.setId(ID);

        CashRegister cashRegisterRandomized = easyRandom.nextObject(CashRegister.class);

        Mockito
                .when(cashRegisterRepository.findById(ID))
                .thenReturn(Optional.of(cashRegisterRandomized));

        Mockito
                .when(cashRegisterRepository.findByNumber(NUMBER))
                .thenReturn(Optional.of(cashRegisterRandomized));

        TypePay typePay = new TypePay();
        typePay.setId(ID);

        Mockito
                .when(cashRegisterRepository.findCasaMarcatByTypePay(typePay))
                .thenReturn(Optional.of(cashRegisterRandomized));

        TypeStatus typeStatus = new TypeStatus();
        typeStatus.setId(ID);

        Mockito
                .when(cashRegisterRepository.findCasaMarcatByTypeStatus(typeStatus))
                .thenReturn(Optional.of(cashRegisterRandomized));

        Mockito
                .when(cashRegisterRepository.findCasaMarcatByTypeStatusAndTypePay(typeStatus, typePay))
                .thenReturn(Optional.of(cashRegisterRandomized));
    }

    @Test
    void whenFindByIdthenReturnCasaMarcat() {
        Optional<CashRegister> found = cashRegisterService.findById(ID);

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
        // when
        Optional<CashRegister> found = cashRegisterService.findByNumber(NUMBER);

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

        // when
        Optional<CashRegister> found = cashRegisterService.findCasaMarcatByTypeStatusAndTypePay(typeStatus, typePay);

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

    @TestConfiguration
    class CashRegisterServiceImplTestContextConfiguration {

        @Bean
        public CashRegisterServiceImpl cashRegisterService() {
            return new CashRegisterServiceImpl(cashRegisterRepository);
        }
    }
}
