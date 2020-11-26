package com.cotiviti.erestaurantauth.configuration;

import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.JWSAlgorithm;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("classpath:blogme-jwt.p12")
    private Resource keyPairFile;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private static final String KEY_ID = "blogme-jwt-id";
    private static final char[] KEY_STORE_PASS = "blogME".toCharArray();
    private static final String KEY_PAIR_ALIAS = "blogme-jwt";



    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(KEY_ID);
        return new JWKSet(builder.build());
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyPairFile, KEY_STORE_PASS);
        return keyStoreKeyFactory.getKeyPair(KEY_PAIR_ALIAS);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        Map<String, String> customHeaders = Collections.singletonMap("kid", KEY_ID);
        return new CustomJWTAccessTokenConverter(customHeaders, keyPair());
    }

    @Bean
    CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    CustomAuthEntryPoint authEntryPoint() {
        return new CustomAuthEntryPoint();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(authEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(this.passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService)
                .exceptionTranslator(new MyWebResponseExceptionTranslator());
    }
}
