package com.javeriana.tool_manager.Interfaces;

/**
 * Interfaz que define una entidad de relación.
 */
public interface IRelationEntity {

    /**
     * Obtiene el ID compuesto de la entidad de relación.
     *
     * @return El ID compuesto de la entidad de relación.
     */
    public ICompoundId getId();

    /**
     * Establece el ID compuesto de la entidad de relación.
     *
     * @param pId El ID compuesto de la entidad de relación.
     */
    public void setId(ICompoundId pId);
}
