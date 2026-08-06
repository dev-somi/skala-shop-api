package com.sk.skala.shopapi.common;

public class Response<T> {
    private String message;
    private T body;

    public Response(String message, T body){
        this.message = message;
        this.body = body;
    }
}
