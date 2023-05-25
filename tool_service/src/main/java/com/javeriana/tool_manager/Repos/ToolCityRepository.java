package com.javeriana.tool_manager.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javeriana.tool_manager.Entities.City;
import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.RelationEntities.ToolCity;
import com.javeriana.tool_manager.RelationEntities.ToolCityId;

/**
 * Repositorio que proporciona métodos para acceder y gestionar los datos de la entidad ToolCity en la base de datos.
 */
public interface ToolCityRepository extends JpaRepository<ToolCity, ToolCityId> {

    /**
     * Busca todas las ciudades asociadas a una herramienta específica.
     *
     * @param toolId El ID de la herramienta.
     * @return Una lista de ciudades asociadas a la herramienta.
     */
    @Query("SELECT tc.city FROM ToolCity tc WHERE tc.tool.id = :toolId")
    List<City> findCitiesByToolId(@Param("toolId") Long toolId);

    /**
     * Busca todas las herramientas asociadas a una ciudad específica.
     *
     * @param cityId El ID de la ciudad.
     * @return Una lista de herramientas asociadas a la ciudad.
     */
    @Query("SELECT tc.tool FROM ToolCity tc WHERE tc.city.id = :cityId")
    List<Tool> findToolsByCityId(@Param("cityId") Long cityId);
}
