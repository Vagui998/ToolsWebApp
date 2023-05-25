package com.javeriana.user_manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para la aplicación User Manager.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configura el filtro de seguridad para permitir todas las solicitudes HTTP sin autenticación.
     *
     * @param http Objeto HttpSecurity para la configuración de seguridad.
     * @return SecurityFilterChain configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }

}
