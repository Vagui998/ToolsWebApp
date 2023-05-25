package com.javeriana.user_manager.Entities;

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

/**
 * Clase que representa un token en el sistema.
 */
@Entity
public class Token {

  /**
   * ID del token.
   */
  @Id
  @GeneratedValue
  @JsonProperty("id")
  public Integer id;

  /**
   * Valor del token.
   */
  @JsonProperty("token")
  @Column(unique = true)
  public String token;

  /**
   * Tipo de token.
   */
  @JsonProperty("tokenType")
  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  /**
   * Indica si el token ha sido revocado.
   */
  @JsonProperty("revoked")
  public boolean revoked;

  /**
   * Indica si el token ha expirado.
   */
  @JsonProperty("expired")
  public boolean expired;

  /**
   * Usuario asociado al token.
   */
  @JsonProperty("user")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  public User user;
}
