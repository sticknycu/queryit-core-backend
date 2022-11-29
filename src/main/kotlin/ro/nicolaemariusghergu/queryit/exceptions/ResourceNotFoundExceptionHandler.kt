package ro.nicolaemariusghergu.queryit.exceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ResourceNotFoundExceptionHandler : ResponseEntityExceptionHandler()/*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ClientResponse> handleResourceNotFoundException(RuntimeException runtimeException) {
        log.debug(runtimeException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }*/