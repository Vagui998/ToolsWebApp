package com.javeriana.auth_manager.Authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una respuesta de validación de token.
 * Contiene un indicador booleano que indica si el token es válido o no.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationResponse {

  /**
   * Indica si el token es válido (true) o no válido (false).
   */
  private boolean isValid;
}
