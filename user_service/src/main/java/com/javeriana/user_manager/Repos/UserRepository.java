package com.javeriana.user_manager.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javeriana.user_manager.Entities.User;

/**
 * Repositorio de datos para la entidad User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
