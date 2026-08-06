package com.sk.skala.shopapi.exception;

public class ParameterException extends RuntimeException {
    private String[] fields;

    public ParameterException(String... fields){
        this.fields = fields;
    }
}
