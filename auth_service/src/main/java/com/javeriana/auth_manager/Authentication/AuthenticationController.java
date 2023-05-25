package com.javeriana.auth_manager.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * El controlador que maneja las operaciones de autenticación y autorización.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  /**
   * Registra un nuevo usuario.
   *
   * @param request El objeto RegisterRequest que contiene los datos de registro.
   * @return El objeto ResponseEntity con la respuesta de la operación de registro.
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  /**
   * Autentica un usuario.
   *
   * @param request El objeto AuthenticationRequest que contiene las credenciales de autenticación.
   * @return El objeto ResponseEntity con la respuesta de la operación de autenticación.
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  /**
   * Renueva el token de acceso.
   *
   * @param request  El objeto HttpServletRequest que representa la solicitud HTTP.
   * @param response El objeto HttpServletResponse que representa la respuesta HTTP.
   * @throws IOException Si ocurre un error al manejar la solicitud o respuesta HTTP.
   */
  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  /**
   * Valida un token de acceso.
   *
   * @param request El objeto TokenValidationRequest que contiene el token a validar.
   * @return El objeto ResponseEntity con la respuesta de la validación del token.
   */
  @PostMapping("/validate-token")
  public ResponseEntity<TokenValidationResponse> validateToken(
      @RequestBody TokenValidationRequest request
  ) {
    boolean isValid = service.validateToken(request.getToken());
    TokenValidationResponse response = new TokenValidationResponse(isValid);
    return ResponseEntity.ok(response);
  }

}
