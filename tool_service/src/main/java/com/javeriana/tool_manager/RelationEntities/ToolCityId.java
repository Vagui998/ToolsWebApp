package com.javeriana.tool_manager.RelationEntities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa el identificador compuesto de la relación ToolCity.
 */
@Embeddable
public class ToolCityId extends AbstractId {

    /**
     * El ID de la herramienta.
     */
    @Setter
    @Getter
    @Column(name = "tool_id")
    private Long toolId;

    /**
     * El ID de la ciudad.
     */
    @Setter
    @Getter
    @Column(name = "city_id")
    private Long cityId;

    /**
     * Construye una nueva instancia de ToolCityId con los IDs de la herramienta y la ciudad.
     *
     * @param pToolId  El ID de la herramienta.
     * @param pCityId  El ID de la ciudad.
     */
    public ToolCityId(Long pToolId, Long pCityId) {
        super();
        this.toolId = pToolId;
        this.cityId = pCityId;
    }

    /**
     * Construye una nueva instancia vacía de ToolCityId.
     */
    public ToolCityId() {
        super();
    }

    /**
     * Calcula y devuelve el valor hash del identificador compuesto.
     *
     * @return El valor hash del identificador compuesto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(toolId, cityId);
    }

    /**
     * Compara el identificador compuesto con otro objeto para verificar si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return true si el identificador compuesto es igual al objeto dado, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ToolCityId other = (ToolCityId) obj;
        return Objects.equals(toolId, other.toolId) &&
                Objects.equals(cityId, other.cityId);
    }

    /**
     * Obtiene el primer componente del identificador compuesto.
     *
     * @return El ID de la herramienta.
     */
    @Override
    public Long getId0() {
        return this.toolId;
    }

    /**
     * Obtiene el segundo componente del identificador compuesto.
     *
     * @return El ID de la ciudad.
     */
    @Override
    public Long getId1() {
        return this.cityId;
    }
}
