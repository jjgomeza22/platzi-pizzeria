package com.platzi.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.platzi.pizzeria.service.enums.RolesEnum.ADMIN;
import static com.platzi.pizzeria.service.enums.RolesEnum.CUSTOMER;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(customRequest -> customRequest
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole(ADMIN.getValue(), CUSTOMER.getValue()) //with **, permit all the GET request
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole(ADMIN.getValue())
                        .requestMatchers(HttpMethod.PUT).hasRole(ADMIN.getValue()) //It does not have patter, it applies to all project
                        .requestMatchers("/api/orders/**").hasRole(ADMIN.getValue()) //to use all the methods you need to be an admin
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService memoryUsers() {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(ADMIN.getValue())
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles(CUSTOMER.getValue())
                .build();

        return new InMemoryUserDetailsManager(adminUser, customer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
