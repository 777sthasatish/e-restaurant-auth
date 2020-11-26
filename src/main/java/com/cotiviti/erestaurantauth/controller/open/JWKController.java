package com.cotiviti.erestaurantauth.controller.open;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FrameworkEndpoint
public class JWKController {
    private final JWKSet jwkSet;
    public JWKController(final JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("/.well-known/jwks.json")
    @ResponseBody
    public Map<String, Object> keys() {
        return jwkSet.toJSONObject();
    }
}
