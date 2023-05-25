package com.javeriana.user_manager.Interfaces;

/**
 * Interfaz IEntity que define los métodos básicos para una entidad.
 */
public interface IEntity {

    /**
     * Obtiene el ID de la entidad.
     *
     * @return ID de la entidad.
     */
    public Long getID();

    /**
     * Obtiene el nombre de la entidad.
     *
     * @return Nombre de la entidad.
     */
    public String getName();

    /**
     * Establece el nombre de la entidad.
     *
     * @param pName Nombre a establecer.
     */
    public void setName(String pName);
}
