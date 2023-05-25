package com.javeriana.auth_manager.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Servicio para la generación, validación y extracción de información de tokens JWT.
 */
@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  /**
   * Extrae el nombre de usuario del token JWT.
   * @param token El token JWT.
   * @return El nombre de usuario extraído del token JWT.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Extrae una reclamación específica del token JWT utilizando una función de reclamaciones.
   * @param token El token JWT.
   * @param claimsResolver La función de reclamaciones que se utilizará para extraer la reclamación deseada.
   * @param <T> El tipo de dato de la reclamación extraída.
   * @return La reclamación extraída del token JWT.
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Genera un token JWT para los detalles de usuario proporcionados.
   * @param userDetails Los detalles del usuario para los que se generará el token.
   * @return El token JWT generado.
   */
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * Genera un token JWT con reclamaciones adicionales para los detalles de usuario proporcionados.
   * @param extraClaims Las reclamaciones adicionales que se agregarán al token JWT.
   * @param userDetails Los detalles del usuario para los que se generará el token.
   * @return El token JWT generado.
   */
  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  /**
   * Genera un token de actualización JWT para los detalles de usuario proporcionados.
   * @param userDetails Los detalles del usuario para los que se generará el token de actualización.
   * @return El token de actualización JWT generado.
   */
  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails,
          long expiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  /**
   * Verifica si un token JWT es válido para los detalles de usuario proporcionados.
   * @param token El token JWT.
   * @param userDetails Los detalles del usuario para los que se realizará la verificación.
   * @return true si el token JWT es válido, false de lo contrario.
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }


  /**
 * Verifica si un token JWT ha expirado.
 * @param token El token JWT a verificar.
 * @return true si el token ha expirado, false de lo contrario.
 */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
 * Extrae la fecha de expiración de un token JWT.
 * @param token El token JWT del que se extraerá la fecha de expiración.
 * @return La fecha de expiración del token JWT.
 */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
 * Extrae todas las reclamaciones (claims) de un token JWT.
 * @param token El token JWT del que se extraerán las reclamaciones.
 * @return Un objeto Claims que contiene todas las reclamaciones del token JWT.
 */
  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
 * Obtiene la clave de firma utilizada para firmar y verificar los tokens JWT.
 * @return La clave de firma.
 */
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
