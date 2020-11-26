package com.cotiviti.erestaurantauth.serializer;

import com.cotiviti.erestaurantauth.dto.HttpErrorMessage;
import com.cotiviti.erestaurantauth.exception.CustomOAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OauthExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {
    public OauthExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(new HttpErrorMessage(value.getStatusCode(), value.getMessage()));
    }
}
