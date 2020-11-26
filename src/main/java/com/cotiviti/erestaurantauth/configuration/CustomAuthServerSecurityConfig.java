package com.cotiviti.erestaurantauth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;

import static com.cotiviti.erestaurantauth.utils.constants.APIConstants.*;

@Import(AuthorizationServerEndpointsConfiguration.class)
@Configuration
@Order(-1)
public class CustomAuthServerSecurityConfig extends AuthorizationServerSecurityConfiguration {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .requestMatchers()
                .antMatchers("/.well-known/jwks.json", ALL_PUBLIC)
                .and()
                .authorizeRequests()
                .antMatchers("/.well-known/jwks.json").permitAll()
                .antMatchers(ALL_PUBLIC).authenticated();
    }
}
