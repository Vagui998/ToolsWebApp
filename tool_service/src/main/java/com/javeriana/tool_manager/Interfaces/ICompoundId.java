package com.javeriana.tool_manager.Interfaces;

/**
 * Interfaz que define un identificador compuesto.
 */
public interface ICompoundId {

    /**
     * Obtiene el primer ID del identificador compuesto.
     *
     * @return El primer ID.
     */
    public Long getId0();

    /**
     * Obtiene el segundo ID del identificador compuesto.
     *
     * @return El segundo ID.
     */
    public Long getId1();
}
