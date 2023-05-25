package com.javeriana.tool_manager.Entities;

import com.javeriana.tool_manager.Interfaces.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Clase abstracta que representa una entidad genérica con atributos comunes.
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
     * @param pId   El ID de la entidad.
     * @param pName El nombre de la entidad.
     */
    public AbstractEntity(Long pId, String pName) {
        this.id = pId;
        this.name = pName;
    }

    /**
     * Constructor de la clase AbstractEntity.
     */
    public AbstractEntity() {

    }

    /**
     * Establece el nombre de la entidad.
     *
     * @param pName El nombre de la entidad.
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * Obtiene el nombre de la entidad.
     *
     * @return El nombre de la entidad.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el ID de la entidad.
     *
     * @return El ID de la entidad.
     */
    public Long getID() {
        return id;
    }
}
