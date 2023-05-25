package com.javeriana.tool_manager.RelationEntities;

import com.javeriana.tool_manager.Interfaces.IRelationEntity;

import jakarta.persistence.MappedSuperclass;

/**
 * Clase abstracta que representa una entidad de relación.
 */
@MappedSuperclass
public abstract class AbstractRelationEntity implements IRelationEntity {

    /**
     * Constructor de la clase AbstractRelationEntity.
     */
    public AbstractRelationEntity() {

    }
}
