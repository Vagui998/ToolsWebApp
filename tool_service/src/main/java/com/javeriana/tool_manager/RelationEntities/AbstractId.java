package com.javeriana.tool_manager.RelationEntities;

import com.javeriana.tool_manager.Interfaces.ICompoundId;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Clase abstracta que representa un ID compuesto.
 */
@Embeddable
public abstract class AbstractId implements Serializable, ICompoundId {

    /**
     * Constructor de la clase AbstractId.
     */
    public AbstractId() {
        
    }
}
