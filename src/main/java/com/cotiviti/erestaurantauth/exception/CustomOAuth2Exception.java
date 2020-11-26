package com.cotiviti.erestaurantauth.exception;

import com.cotiviti.erestaurantauth.serializer.OauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = OauthExceptionSerializer.class)
@Getter
public class CustomOAuth2Exception extends OAuth2Exception {
    private int statusCode;
    public CustomOAuth2Exception(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }
}
