package com.javeriana.tool_manager.Interfaces;

/**
 * Interfaz que define una entidad.
 */
public interface IEntity {

    /**
     * Obtiene el ID de la entidad.
     *
     * @return El ID de la entidad.
     */
    public Long getID();

    /**
     * Obtiene el nombre de la entidad.
     *
     * @return El nombre de la entidad.
     */
    public String getName();

    /**
     * Establece el nombre de la entidad.
     *
     * @param pName El nombre de la entidad.
     */
    public void setName(String pName);
}
