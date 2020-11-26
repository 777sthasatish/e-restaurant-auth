package com.cotiviti.erestaurantauth.exceptionhandler;

import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.satish.fieldvalidator.restadapter.exception.RestAdapterExceptionHandler;

@RestControllerAdvice
@Import(RestAdapterExceptionHandler.class)
public class RestExceptionalHandler {
}
