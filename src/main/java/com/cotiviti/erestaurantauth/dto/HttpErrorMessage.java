package com.cotiviti.erestaurantauth.dto;


import com.cotiviti.erestaurantauth.dto.base.BaseMessageResponse;

public class HttpErrorMessage extends BaseMessageResponse {
    public HttpErrorMessage(int code, String message) {
        super(code, message);
    }
}
