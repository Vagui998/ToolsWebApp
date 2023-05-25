package com.javeriana.tool_manager.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de validación de token.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationRequest {

  /**
   * Token a validar.
   */
  private String token;
}
