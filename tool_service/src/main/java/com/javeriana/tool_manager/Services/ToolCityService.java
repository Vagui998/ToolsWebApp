package com.javeriana.tool_manager.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javeriana.tool_manager.Entities.City;
import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.RelationEntities.ToolCity;
import com.javeriana.tool_manager.Repos.ToolCityRepository;

/**
 * Servicio que proporciona métodos para acceder y gestionar los datos de la entidad ToolCity.
 */
@Service
public class ToolCityService {

    @Autowired
    ToolCityRepository repo;

    /**
     * Obtiene todas las relaciones ToolCity almacenadas en la base de datos.
     *
     * @return Una lista de todas las relaciones ToolCity.
     */
    public List<ToolCity> getAll() {
        return (List<ToolCity>) repo.findAll();
    }

    /**
     * Obtiene todas las ciudades asociadas a una herramienta específica.
     *
     * @param pId El ID de la herramienta.
     * @return Una lista de ciudades asociadas a la herramienta.
     */
    public List<City> getCitiesByTool(Long pId) {
        return repo.findCitiesByToolId(pId);
    }

    /**
     * Obtiene todas las herramientas asociadas a una ciudad específica.
     *
     * @param pId El ID de la ciudad.
     * @return Una lista de herramientas asociadas a la ciudad.
     */
    public List<Tool> getToolsByCity(Long pId) {
        return repo.findToolsByCityId(pId);
    }
}
