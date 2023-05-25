package com.javeriana.tool_manager.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.tool_manager.Entities.City;
import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.RelationEntities.ToolCity;
import com.javeriana.tool_manager.Services.ToolCityService;

/**
 * Clase controladora que maneja las solicitudes relacionadas con la entidad ToolCity.
 */
@RestController
@RequestMapping("/toolCity")
public class ToolCityController {

    @Autowired
    private ToolCityService service;

    /**
     * Obtiene todas las relaciones ToolCity existentes.
     *
     * @return Una lista de objetos ToolCity.
     */
    @GetMapping(value = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ToolCity> getAll() {
        return service.getAll();
    }

    /**
     * Obtiene todas las ciudades asociadas a una herramienta específica.
     *
     * @param pId El ID de la herramienta.
     * @return Una lista de objetos City.
     */
    @GetMapping(value = "/getCities/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<City> getCitiesByTool(@PathVariable(value = "id") Long pId) {
        return service.getCitiesByTool(pId);
    }

    /**
     * Obtiene todas las herramientas asociadas a una ciudad específica.
     *
     * @param pId El ID de la ciudad.
     * @return Una lista de objetos Tool.
     */
    @GetMapping(value = "/getTools/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Tool> getToolsByCity(@PathVariable(value = "id") Long pId) {
        return service.getToolsByCity(pId);
    }
}
