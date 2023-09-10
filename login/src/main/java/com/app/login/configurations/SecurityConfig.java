package com.app.login.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .cors(customizer -> customizer.configurationSource(corsConfigurationSource())) // <-- CORS設定を適用
        .csrf(csrf -> csrf.disable()) // CSRF protectionを無効
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/api/login").permitAll() // login APIは全てのユーザーにアクセスを許可
                .anyRequest().authenticated()
        )
        .formLogin(formLogin -> 
            formLogin.defaultSuccessUrl("/index.html") // 認証成功後にリダイレクトするURL
        );

    return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); 
        configuration.addAllowedOrigin("http://127.0.0.1:5500/");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 全てのURLパスにこのCORS設定を適用
        
        return source;
    }
}
