package com.cotiviti.erestaurantauth.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseMessageResponse {
    int code;
    String message;
}
