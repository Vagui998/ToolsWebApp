package com.javeriana.tool_manager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

import jakarta.persistence.InheritanceType;

/**
 * Clase que representa una marca de herramienta.
 */
@Entity
@Table(name = "brand")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Brand extends AbstractEntity {

    /**
     * Constructor de la clase Brand.
     *
     * @param pId     El ID de la marca.
     * @param pName   El nombre de la marca.
     * @param pImgUrl La URL de la imagen de la marca.
     */
    public Brand(Long pId, String pName, String pImgUrl) {
        super(pId, pName);
    }

    /**
     * Constructor de la clase Brand.
     */
    public Brand() {

    }
}
