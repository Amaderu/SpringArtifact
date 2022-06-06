package com.amaderu.client.config;

import com.amaderu.client.service.ClientAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    ClientAuthenticationSuccessHandler successHandler;

    private static final String[] WHITE_LIST= {
            "/hello",
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*"
    };
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    /*@Bean(name = {"securityFilterChain"})*/
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                    .antMatchers(WHITE_LIST).permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer.loginPage("/oauth2/authorization/api-client-oidc").successHandler(successHandler))
                .oauth2Client(Customizer.withDefaults());
                /*.antMatchers("/**").hasRole("USER")
                .and()
                .formLogin();*/
                /*.antMatchers("/register").hasAnyRole("user").anyRequest().permitAll();*/
        return http.build();
    }
}
