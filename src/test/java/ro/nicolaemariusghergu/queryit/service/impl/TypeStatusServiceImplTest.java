package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.queryit.exceptions.ResourceNotFoundException;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.service.data.TypeStatusService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TypeStatusServiceImplTest {

    private static final Long ID = 1L;
    private static final String TYPE = "BLOCATA";

    @Autowired
    TypeStatusService typeStatusService;

    @Test
    void whenFindByIdthenReturnTypePay() {
        // given
        TypeStatus typeStatus = new TypeStatus();
        typeStatus.setId(ID);

        // when - Without Mock
        Optional<TypeStatus> found = typeStatusService.findById(typeStatus.getId());

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getType()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }

    @Test
    void whenFindByTypethenReturnTypePay() {
        // given
        TypeStatus typeStatus = new TypeStatus();
        typeStatus.setType(TYPE);

        // when - Without Mock
        Optional<TypeStatus> found = typeStatusService.findByType(typeStatus.getType());

        // then
        found.ifPresentOrElse(typePayFounded -> assertNotNull(typePayFounded.getId()), () -> {
            throw new ResourceNotFoundException("Resource requested has not been found");
        });
    }
}
