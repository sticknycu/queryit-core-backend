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
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.repository.data.TypePayRepository;
import ro.nicolaemariusghergu.queryit.service.data.TypePayService;
import ro.nicolaemariusghergu.queryit.service.impl.data.TypePayServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TypePayServiceImplTest {

    private static final Long ID = 1L;
    private static final String TYPE = "CASH";

    @Autowired
    TypePayService typePayService;

    @MockBean
    TypePayRepository typePayRepository;

    @BeforeEach
    void configurateBeans() {
        EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().collectionSizeRange(1, 2));

        TypePay typePay = new TypePay();
        typePay.setId(ID);

        TypePay typePayRandomized = easyRandom.nextObject(TypePay.class);

        Mockito
                .when(typePayRepository.findById(ID))
                .thenReturn(Optional.of(typePayRandomized));

        Mockito
                .when(typePayRepository.findByType(TYPE))
                .thenReturn(Optional.of(typePayRandomized));
    }

    @Test
    void whenFindByIdthenReturnTypePay() {
        // when
        Optional<TypePay> found = typePayService.findById(ID);

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getType()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByTypethenReturnTypePay() {
        // when
        Optional<TypePay> found = typePayService.findByType(TYPE);

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getId()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @TestConfiguration
    class TypePayServiceImplTestContextConfiguration {

        @Bean
        public TypePayServiceImpl typePayService() {
            return new TypePayServiceImpl(typePayRepository);
        }
    }
}

