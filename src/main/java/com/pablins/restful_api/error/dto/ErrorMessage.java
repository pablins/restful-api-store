package com.pablins.restful_api.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Plantilla que representa la respuesta que le mostraremos al cliente
public class ErrorMessage {
    private HttpStatus status;//Puede ser Internal Server Error, Not found, bad request, etc
    private String message;//mensaje para indicar lo que ocurrió en el servidor
}
