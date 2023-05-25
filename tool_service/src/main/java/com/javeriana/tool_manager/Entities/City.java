package com.javeriana.tool_manager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

/**
 * Clase que representa una ciudad.
 */
@Entity
@Table(name = "city")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class City extends AbstractEntity {

    /**
     * Constructor de la clase City.
     *
     * @param pId   El ID de la ciudad.
     * @param pName El nombre de la ciudad.
     */
    public City(Long pId, String pName) {
        super(pId, pName);
    }

    /**
     * Constructor de la clase City.
     */
    public City() {

    }
}
