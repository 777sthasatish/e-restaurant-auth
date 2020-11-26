package com.cotiviti.erestaurantauth.configuration;

import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.Map;

public class CustomJWTAccessTokenConverter extends JwtAccessTokenConverter {
    private Map<String, String> customHeaders;
    private final RsaSigner signer;
    private JsonParser objectMapper = JsonParserFactory.create();

    public CustomJWTAccessTokenConverter(Map<String, String> customHeaders, KeyPair keyPair) {
        super();
        super.setKeyPair(keyPair);
        this.customHeaders = customHeaders;
        signer = new RsaSigner((RSAPrivateKey)keyPair.getPrivate());
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String content;
        try {
            content = objectMapper
                    .formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
           return JwtHelper.encode(content, this.signer, this.customHeaders).getEncoded();
        }
        catch (Exception e) {
            throw new IllegalStateException("Cannot convert access token to JSON", e);
        }
    }
}
