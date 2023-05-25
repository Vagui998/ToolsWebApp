package com.javeriana.tool_manager.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.Repos.ToolRepository;

/**
 * Servicio que proporciona métodos para acceder y gestionar los datos de la entidad Tool mediante operaciones CRUD.
 */
@Service
public class ToolCrudService {

    @Autowired
    ToolRepository repo;

    /**
     * Obtiene todas las herramientas almacenadas en la base de datos.
     *
     * @return Una lista de todas las herramientas.
     */
    public List<Tool> getAll() {
        return (List<Tool>) repo.findAll();
    }

    /**
     * Obtiene una herramienta específica por su ID.
     *
     * @param pId El ID de la herramienta.
     * @return La herramienta encontrada, o null si no se encuentra ninguna coincidencia.
     */
    public Tool getByID(Long pId) {
        return repo.findById(pId).orElse(null);
    }

    /**
     * Crea una nueva entrada de herramienta en la base de datos.
     *
     * @param pEntity La entidad Tool a guardar.
     */
    public void newEntry(Tool pEntity) {
        repo.save(pEntity);
    }

    /**
     * Actualiza una entrada de herramienta existente en la base de datos.
     *
     * @param pEntity La entidad Tool actualizada.
     */
    public void updateEntry(Tool pEntity) {
        Optional<Tool> targetEntity = repo.findById(pEntity.getID());
        if (targetEntity.isPresent()) {
            repo.save(pEntity);
        } else {
            System.out.println("The requested ID is not found");
        }
    }

    /**
     * Elimina una entrada de herramienta de la base de datos por su ID.
     *
     * @param pId El ID de la herramienta a eliminar.
     */
    public void deleteEntry(Long pId) {
        Optional<Tool> targetEntity = repo.findById(pId);
        if (targetEntity.isPresent()) {
            repo.delete(targetEntity.get());
        } else {
            System.out.println("The requested ID is not found");
        }
    }

    /**
     * Obtiene una página de herramientas de la base de datos.
     *
     * @param pageable La configuración de paginación.
     * @return Una página de herramientas.
     */
    public Page<Tool> paginas(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
