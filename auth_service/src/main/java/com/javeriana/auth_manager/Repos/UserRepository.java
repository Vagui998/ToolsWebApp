package com.javeriana.auth_manager.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javeriana.auth_manager.Entities.User;


/**
 * Interfaz que define un repositorio para la entidad User.
 * Extiende JpaRepository de Spring Data JPA para heredar operaciones CRUD estándar.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Optional que contiene el usuario si se encuentra, o un Optional vacío si no se encuentra.
     */
    Optional<User> findByUsername(String username);
}
