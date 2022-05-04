package com.example.OauthResourceServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //In newest versions implicts another
        http
                .authorizeRequests()
                .mvcMatchers("/api/**")
                //TODO fix to another scope
                .access("hasAuthority('SCOPE_api.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
