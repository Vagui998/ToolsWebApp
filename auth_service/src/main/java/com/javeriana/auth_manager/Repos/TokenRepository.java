package com.javeriana.auth_manager.Repos;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javeriana.auth_manager.Entities.Token;

/**
 * Interfaz que define un repositorio para la entidad Token.
 * Extiende JpaRepository de Spring Data JPA para heredar operaciones CRUD estándar.
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

  /**
   * Busca todos los tokens válidos asociados a un usuario dado.
   * Los tokens válidos son aquellos que no han expirado ni han sido revocados.
   *
   * @param id Identificador del usuario.
   * @return Lista de tokens válidos.
   */
  @Query(value = """
          select t from Token t inner join User u
          on t.user.id = u.id
          where u.id = :id and (t.expired = false or t.revoked = false)
          """)
  List<Token> findAllValidTokenByUser(Integer id);

  /**
   * Busca un token por su valor.
   *
   * @param token Valor del token.
   * @return Optional que contiene el token si se encuentra, o un Optional vacío si no se encuentra.
   */
  Optional<Token> findByToken(String token);
}
