package com.javeriana.auth_manager.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de registro de usuario.
 * Contiene los campos necesarios para crear una nueva cuenta de usuario.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  /**
   * El nombre de usuario para la nueva cuenta.
   */
  private String username;

  /**
   * La contraseña para la nueva cuenta.
   */
  private String password;
}