package com.pablins.restfull_api.error;

import com.pablins.restfull_api.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice//Le decimos al contener de spring boot que esta clase manejará las excepciones globales
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(StoreNotFoundException.class)//Cuando aparezaca excepcion StoreNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)//Respuesta que daremos
    public ResponseEntity<ErrorMessage> storeNotFoundException(StoreNotFoundException exception) {
        //construimos nuestro objeto plantilla
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

}
