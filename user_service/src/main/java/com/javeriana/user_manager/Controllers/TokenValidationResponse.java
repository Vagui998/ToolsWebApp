package com.javeriana.user_manager.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de validación de token.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationResponse {

  /**
   * Indica si el token es válido o no.
   */
  private boolean isValid;
}
