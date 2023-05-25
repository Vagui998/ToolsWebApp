package com.javeriana.auth_manager.Authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de validación de token.
 * Contiene el token que se desea validar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationRequest {

  /**
   * El token que se desea validar.
   */
  private String token;
}
