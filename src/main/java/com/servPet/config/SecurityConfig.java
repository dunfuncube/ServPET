package com.servPet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/register", "/login", "/reset-password", "/checkEmail").permitAll()
            .antMatchers("/dashboard", "/members/**").authenticated()
            .and()
            .csrf()
            .ignoringAntMatchers("/checkEmail") // 忽略特定路徑的 CSRF
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true)
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");
        return http.build();
    }

}
