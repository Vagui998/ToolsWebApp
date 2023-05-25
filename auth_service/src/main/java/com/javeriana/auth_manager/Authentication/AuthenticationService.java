package com.javeriana.auth_manager.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javeriana.auth_manager.Configuration.JwtService;
import com.javeriana.auth_manager.Entities.Role;
import com.javeriana.auth_manager.Entities.Token;
import com.javeriana.auth_manager.Entities.TokenType;
import com.javeriana.auth_manager.Entities.User;
import com.javeriana.auth_manager.Repos.TokenRepository;
import com.javeriana.auth_manager.Repos.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Clase que proporciona servicios relacionados con la autenticación de usuarios.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Registra un nuevo usuario en el sistema.
   *
   * @param request Objeto que contiene los datos de registro del usuario.
   * @return La respuesta de autenticación que incluye el token de acceso y el token de actualización.
   */
  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.User)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  /**
   * Autentica a un usuario en el sistema.
   *
   * @param request Objeto que contiene las credenciales de autenticación del usuario.
   * @return La respuesta de autenticación que incluye el token de acceso y el token de actualización.
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = repository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  /**
   * Guarda un token de usuario en el repositorio.
   *
   * @param user     El usuario asociado al token.
   * @param jwtToken El token JWT a guardar.
   */
  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

   /**
   * Revoca todos los tokens válidos del usuario especificado.
   *
   * @param user El usuario cuyos tokens se van a revocar.
   */
  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  /**
   * Renueva el token de acceso utilizando el token de actualización.
   *
   * @param request  El objeto HttpServletRequest que contiene el token de actualización en el encabezado de autorización.
   * @param response El objeto HttpServletResponse utilizado para enviar la respuesta.
   * @throws IOException Si ocurre un error durante la escritura de la respuesta.
   */
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String username;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    username = jwtService.extractUsername(refreshToken);
    if (username != null) {
      var user = this.repository.findByUsername(username)
          .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

  /**
   * Valida la autenticidad y vigencia de un token.
   *
   * @param token El token que se va a validar.
   * @return true si el token es válido, false de lo contrario.
   */
  public boolean validateToken(String token) {
    try {
      String username = jwtService.extractUsername(token);
      if (username != null) {
        User user = repository.findByUsername(username).orElse(null);
        if (user != null) {
          return jwtService.isTokenValid(token, user);
        }
      }
      return false;
    } catch (Exception e) {
      // Handle the SignatureException here
      return false; // Token is invalid
    }
  }
}
