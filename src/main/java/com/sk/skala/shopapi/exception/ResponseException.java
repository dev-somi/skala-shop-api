package com.sk.skala.shopapi.exception;

public class ResponseException extends RuntimeException{
    private Error error;
    public ResponseException(Error error){
        super(error.getMessage());
        this.error = error;
    }    
}
