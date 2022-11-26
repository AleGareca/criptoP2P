package com.unq.desa.criptoP2P.exeption;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;


import javax.validation.ValidationException;


@Slf4j
@ControllerAdvice
@RestController
class GlobalExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity validationException(ValidationException ex, HandlerMethod handlerMethod) {
        log.error("Validation exception handled in service: {}, message: {}", handlerMethod.getMethod().getDeclaringClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntentionNotFound.class)
    public ResponseEntity intentionNotFound(DataIntentionNotFound ex,HandlerMethod handlerMethod){
        log.error("Intentioin exception handled in service: {}, message: {}", handlerMethod.getMethod().getDeclaringClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }


}