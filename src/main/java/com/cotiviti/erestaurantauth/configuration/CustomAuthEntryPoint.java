package com.cotiviti.erestaurantauth.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements
        AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            setExceptionTranslator(new MyWebResponseExceptionTranslator());
            doHandle(request, response, authException);
    }
}


