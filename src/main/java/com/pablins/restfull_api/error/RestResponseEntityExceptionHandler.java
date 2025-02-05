package com.pablins.restfull_api.error;

import com.pablins.restfull_api.error.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//Le decimos al contener de spring boot que esta clase manejará las excepciones globales
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(StoreNotFoundException.class)//Cuando aparezaca excepcion StoreNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)//Respuesta que daremos
    public ResponseEntity<ErrorMessage> storeNotFoundException(StoreNotFoundException exception) {
        //construimos nuestro objeto plantilla
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> mapErrors = new HashMap<>();

        //De la excepción obtenemos la lista de errores
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            //para cada error encontrado, lo agregamos en nuestro mapa. El campo y el mensaje
            mapErrors.put(e.getField(), e.getDefaultMessage());
        });

        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
        return ResponseEntity.status(status).body(mapErrors);
    }
}
