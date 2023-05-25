package com.javeriana.auth_manager.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un token de autenticación en el sistema.
 * Un token es utilizado para autenticar y autorizar a los usuarios en las diferentes partes del sistema.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

  /**
   * Identificador único del token.
   */
  @Id
  @GeneratedValue
  @JsonProperty("id")
  public Integer id;

  /**
   * Valor del token.
   * Representa el token en sí, que se utiliza para autenticar y autorizar a los usuarios.
   */
  @JsonProperty("token")
  @Column(unique = true)
  public String token;

  /**
   * Tipo de token.
   * Especifica el tipo de token, como "Bearer" u otro valor definido por el enum TokenType.
   * El valor predeterminado es TokenType.BEARER.
   */
  @JsonProperty("tokenType")
  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  /**
   * Indica si el token ha sido revocado.
   * Si está marcado como revocado, el token ya no es válido y no se puede utilizar para autenticación.
   */
  @JsonProperty("revoked")
  public boolean revoked;

  /**
   * Indica si el token ha expirado.
   * Si está marcado como expirado, el token ya no es válido debido a que ha superado su fecha de expiración.
   */
  @JsonProperty("expired")
  public boolean expired;

  /**
   * Usuario asociado al token.
   * Representa el usuario al que pertenece el token.
   * La relación entre Token y User es Many-to-One, es decir, varios tokens pueden pertenecer al mismo usuario.
   */
  @JsonProperty("user")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  public User user;
}

