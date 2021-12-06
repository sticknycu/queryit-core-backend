package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.queryit.exceptions.ResourceNotFoundException;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.service.data.TypePayService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TypePayServiceImplTest {

    private static final Long ID = 1L;
    private static final String TYPE = "CASH";

    @Autowired
    TypePayService typePayService;

    @Test
    void whenFindByIdthenReturnTypePay() {
        // given
        TypePay typePay = new TypePay();
        typePay.setId(ID);

        // when - Without Mock
        Optional<TypePay> found = typePayService.findById(typePay.getId());

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getType()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByTypethenReturnTypePay() {
        // given
        TypePay typePay = new TypePay();
        typePay.setType(TYPE);

        // when - Without Mock
        Optional<TypePay> found = typePayService.findByType(typePay.getType());

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getId()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }
}

