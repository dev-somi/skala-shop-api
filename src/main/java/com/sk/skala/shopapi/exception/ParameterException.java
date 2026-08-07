package com.sk.skala.shopapi.exception;

import lombok.Getter;

@Getter
public class ParameterException extends RuntimeException {
    private String[] fields;

    public ParameterException(String... fields){
        this.fields = fields;
    }
}
