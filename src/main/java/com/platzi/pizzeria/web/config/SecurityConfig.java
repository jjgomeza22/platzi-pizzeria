package com.platzi.pizzeria.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.platzi.pizzeria.service.enums.RolesEnum.ADMIN;
import static com.platzi.pizzeria.service.enums.RolesEnum.CUSTOMER;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customRequest -> customRequest
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/customers/**").hasAnyRole(ADMIN.getValue(), CUSTOMER.getValue())
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole(ADMIN.getValue(), CUSTOMER.getValue()) //with **, permit all the GET request
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole(ADMIN.getValue())
                        .requestMatchers(HttpMethod.PUT).hasRole(ADMIN.getValue()) //It does not have patter, it applies to all project
                        .requestMatchers(HttpMethod.POST, "/api/orders/random").hasAuthority("random_order")
                        .requestMatchers("/api/orders/**").hasRole(ADMIN.getValue()) //to use all the methods you need to be an admin
                        .anyRequest().authenticated())
                .sessionManagement(customManagement -> customManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
