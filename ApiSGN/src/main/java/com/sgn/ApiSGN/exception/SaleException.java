package com.sgn.ApiSGN.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SaleException extends ResponseStatusException {

    public SaleException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
