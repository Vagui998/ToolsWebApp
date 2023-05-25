package com.javeriana.auth_manager.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javeriana.auth_manager.Repos.UserRepository;

/**
 * Clase de configuración de la aplicación.
 * Configura los beans necesarios para la autenticación y el cifrado de contraseñas.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository repository;

  /**
   * Crea un bean para el servicio de detalles de usuario.
   * @return Una implementación de UserDetailsService.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Crea un bean para el proveedor de autenticación.
   * @return Un proveedor de autenticación basado en DaoAuthenticationProvider.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Crea un bean para el administrador de autenticación.
   * @param config La configuración de autenticación.
   * @return Un administrador de autenticación.
   * @throws Exception Si ocurre algún error al obtener el administrador de autenticación.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Crea un bean para el codificador de contraseñas.
   * @return Un codificador de contraseñas basado en BCryptPasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

