package com.javeriana.tool_manager;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración personalizada para habilitar CORS (Cross-Origin Resource Sharing) en la aplicación.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Permite el acceso CORS para todas las rutas de la aplicación desde el origen "http://localhost:4200".
     * Se permiten los métodos GET, POST, PUT y DELETE, y se permiten todos los encabezados.
     * También se permite el envío de cookies.
     *
     * @param registry El registro de configuración CORS.
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
