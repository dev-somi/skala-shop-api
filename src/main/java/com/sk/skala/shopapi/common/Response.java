package com.sk.skala.shopapi.common;

import lombok.Getter;

@Getter
public class Response<T> {
    private String message;
    private T body;

    public Response(String message, T body){
        this.message = message;
        this.body = body;
    }
}
