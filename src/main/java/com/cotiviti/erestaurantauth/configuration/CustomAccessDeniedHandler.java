package com.cotiviti.erestaurantauth.configuration;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler extends AbstractOAuth2SecurityExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        setExceptionTranslator(new MyWebResponseExceptionTranslator());
        doHandle(request, response, accessDeniedException);
    }
}
