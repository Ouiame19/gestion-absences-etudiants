package com.backend.gestion_absences.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        
        return http.build();
    }
    
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        var corsConfig = new org.springframework.web.cors.CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        
        var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
