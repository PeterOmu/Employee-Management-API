package com.interswitch.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/employees/**", "/api/departments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/health/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employees", "/api/departments/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**", "/api/departments/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**", "/api/departments/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**", "/api/departments/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.realmName("Employee API"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("admin")
                .password("{noop}admin123")   // {noop} = no encoding for simplicity
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}