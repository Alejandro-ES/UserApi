package com.example.user.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/swagger-ui**").permitAll()  // Permitir acceso a Swagger UI
                        .requestMatchers("/v3/api-docs*/**").permitAll()  // Permitir acceso a la documentación de Swagger
                        .requestMatchers("/swagger-ui/**").permitAll()  // Permitir acceso a la UI de Swagger
                        .requestMatchers("/api/v1/users").permitAll()  // Permitir acceso a tu endpoint /api/v1/users
                        .anyRequest().authenticated()  // Requiere autenticación para otras solicitudes
        )
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}