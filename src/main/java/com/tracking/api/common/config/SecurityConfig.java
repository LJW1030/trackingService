package com.tracking.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize.shouldFilterAllDispatcherTypes(false)
        	.anyRequest()
            .permitAll()
            )
            .httpBasic()
            .disable()
            .formLogin()
            .disable()
            .cors()
            .disable()
            .csrf()
            .disable()
            .build();
    }

}
