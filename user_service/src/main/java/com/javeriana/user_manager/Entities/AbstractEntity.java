package com.javeriana.user_manager.Entities;

import com.javeriana.user_manager.Interfaces.IEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Clase abstracta que representa una entidad genérica con atributos comunes como ID y nombre.
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Constructor de la clase AbstractEntity.
     *
     * @param pId   ID de la entidad.
     * @param pName Nombre de la entidad.
     */
    public AbstractEntity(Long pId, String pName) {
        this.id = pId;
        this.name = pName;
    }

    /**
     * Constructor vacío de la clase AbstractEntity.
     */
    public AbstractEntity() {

    }

    /**
     * Establece el nombre de la entidad.
     *
     * @param pName Nuevo nombre de la entidad.
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * Obtiene el nombre de la entidad.
     *
     * @return Nombre de la entidad.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el ID de la entidad.
     *
     * @return ID de la entidad.
     */
    public Long getID() {
        return id;
    }
}
