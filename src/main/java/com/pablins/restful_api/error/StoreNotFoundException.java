package com.pablins.restful_api.error;

public class StoreNotFoundException extends Exception {

    public StoreNotFoundException(String message) {//Sobreescribimos el constructor que recibe un mensaje dado que es lo que queremos
        super(message);
    }
}
