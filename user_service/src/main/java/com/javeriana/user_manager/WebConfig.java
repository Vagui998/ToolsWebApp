package com.javeriana.user_manager;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS (Cross-Origin Resource Sharing) para permitir el acceso desde http://localhost:4200.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Agrega las configuraciones de CORS al registro.
     *
     * @param registry Registro de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
