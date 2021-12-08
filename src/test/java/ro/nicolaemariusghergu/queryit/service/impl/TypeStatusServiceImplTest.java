package ro.nicolaemariusghergu.queryit.service.impl;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeAll;
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
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.repository.data.TypePayRepository;
import ro.nicolaemariusghergu.queryit.repository.data.TypeStatusRepository;
import ro.nicolaemariusghergu.queryit.service.data.TypeStatusService;
import ro.nicolaemariusghergu.queryit.service.impl.data.TypePayServiceImpl;
import ro.nicolaemariusghergu.queryit.service.impl.data.TypeStatusServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TypeStatusServiceImplTest {

    private static final Long ID = 1L;
    private static final String TYPE = "BLOCATA";

    @Autowired
    TypeStatusService typeStatusService;

    @MockBean
    TypeStatusRepository typeStatusRepository;

    @TestConfiguration
    class TypeStatusServiceImplTestContextConfiguration {

        @Bean
        public TypeStatusServiceImpl typeStatusService() {
            return new TypeStatusServiceImpl(typeStatusRepository);
        }
    }

    @BeforeEach
    void configurateBeans() {
        EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().collectionSizeRange(1, 2));

        TypeStatus typeStatus = new TypeStatus();
        typeStatus.setId(ID);

        TypeStatus typeStatusRandomized = easyRandom.nextObject(TypeStatus.class);

        Mockito
                .when(typeStatusRepository.findById(ID))
                .thenReturn(Optional.of(typeStatusRandomized));

        Mockito
                .when(typeStatusRepository.findByType(TYPE))
                .thenReturn(Optional.of(typeStatusRandomized));
    }


    @Test
    void whenFindByIdthenReturnTypePay() {
        // when
        Optional<TypeStatus> found = typeStatusService.findById(ID);

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getType()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByTypethenReturnTypePay() {
        // when
        Optional<TypeStatus> found = typeStatusService.findByType(TYPE);

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getId()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }
}
