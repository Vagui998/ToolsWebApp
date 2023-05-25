package com.javeriana.auth_manager.Configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS para permitir solicitudes desde un origen específico.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Agrega la configuración de CORS al registro de CORS.
   * @param registry El registro de CORS.
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
