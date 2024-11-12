package com.example.topic2.config;

import com.example.topic2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        return userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm -> {
                    httpForm.loginPage("/login").permitAll() // Bejelentkezési oldal elérhetősége
                            .defaultSuccessUrl("/recommend/recommendForm", true); // Sikeres bejelentkezés után átirányít a /recommendForm oldalra
                })

                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/register", "/css/**" , "/js/**" , "/h2-console/**" , "/recommend/**").permitAll() // Elérési útvonalak, amikhez nem szükséges belépés
                            .anyRequest().authenticated(); // Minden más elérési útvonal belépést igényel
                })
                .headers(headers -> headers.frameOptions().sameOrigin()) // Engedélyezi a frame-eket ugyanazon domainen belül
                .build();
    }
}
