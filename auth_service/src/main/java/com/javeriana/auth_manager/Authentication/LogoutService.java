package com.javeriana.auth_manager.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.javeriana.auth_manager.Repos.TokenRepository;

/**
 * Servicio encargado de gestionar el proceso de cierre de sesión de un usuario.
 * Implementa la interfaz LogoutHandler de Spring Security.
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;

  /**
   * Método de cierre de sesión que invalida y revoca el token de acceso del usuario.
   *
   * @param request        La solicitud HTTP de cierre de sesión.
   * @param response       La respuesta HTTP asociada.
   * @param authentication La autenticación del usuario.
   */
  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    var storedToken = tokenRepository.findByToken(jwt)
        .orElse(null);
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }
  }
}


