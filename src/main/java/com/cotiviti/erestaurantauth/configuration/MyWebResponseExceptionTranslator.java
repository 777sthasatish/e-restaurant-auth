package com.cotiviti.erestaurantauth.configuration;

import com.cotiviti.erestaurantauth.exception.CustomOAuth2Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

public class MyWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> exceptionResponseEntity = super.translate(e);
        return ResponseEntity.status(exceptionResponseEntity.getStatusCode())
                .headers(exceptionResponseEntity.getHeaders())
                .body(new CustomOAuth2Exception(e.getMessage(), exceptionResponseEntity.getStatusCodeValue()));
    }
}

