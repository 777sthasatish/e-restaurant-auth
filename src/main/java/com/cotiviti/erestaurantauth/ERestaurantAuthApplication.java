package com.cotiviti.erestaurantauth;

import com.satish.fieldvalidator.datajpaadapter.annotation.EnableFieldValidatorData;
import com.satish.fieldvalidator.domain.annotation.EnableFieldValidatorDom;
import com.satish.fieldvalidator.mvcadapter.annotation.EnableUI;
import com.satish.fieldvalidator.restadapter.annotation.EnableFieldValidatorRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFieldValidatorDom
@EnableFieldValidatorData
@EnableFieldValidatorRest
@EnableUI
public class ERestaurantAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERestaurantAuthApplication.class, args);
	}

}
