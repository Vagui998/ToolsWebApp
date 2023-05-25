package com.javeriana.user_manager.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.javeriana.user_manager.Entities.User;
import com.javeriana.user_manager.Repos.UserRepository;

/**
 * Servicio que realiza operaciones CRUD (Create, Read, Update, Delete) en la entidad User.
 */
@Service
public class UserCrudService {

    @Autowired
    UserRepository repo;

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de todos los usuarios.
     */
    public List<User> getAll() {
        return (List<User>) repo.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param pId ID del usuario.
     * @return Usuario correspondiente al ID, o null si no se encuentra.
     */
    public User getByID(Long pId) {
        return repo.findById(pId).orElse(null);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param pEntity Usuario a crear.
     */
    public void newEntry(User pEntity) {
        repo.save(pEntity);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param pEntity Usuario a actualizar.
     */
    public void updateEntry(User pEntity) {
        Optional<User> targetEntity = repo.findById(pEntity.getID());
        if (targetEntity.isPresent()) {
            repo.save(pEntity);
        } else {
            System.out.println("The requested ID is not found");
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param pId ID del usuario a eliminar.
     */
    public void deleteEntry(Long pId) {
        Optional<User> targetEntity = repo.findById(pId);
        if (targetEntity.isPresent()) {
            repo.delete(targetEntity.get());
        } else {
            System.out.println("The requested ID is not found");
        }
    }

    /**
     * Obtiene una página de usuarios paginados.
     *
     * @param pageable Objeto Pageable para la paginación.
     * @return Página de usuarios.
     */
    public Page<User> paginas(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
