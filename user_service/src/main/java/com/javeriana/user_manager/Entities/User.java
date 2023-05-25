package com.javeriana.user_manager.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.InheritanceType;

/**
 * Clase que representa a un usuario en el sistema.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends AbstractEntity {

    /**
     * Contraseña del usuario.
     */
    @Setter
    @Getter
    @Column(name = "pass")
    private String password;

    /**
     * Rol del usuario.
     */
    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
