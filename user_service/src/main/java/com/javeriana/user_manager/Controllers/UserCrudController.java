package com.javeriana.user_manager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javeriana.user_manager.Entities.User;
import com.javeriana.user_manager.Services.UserCrudService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserCrudController
{    

    @Autowired
    protected UserCrudService service;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
   
    /**
     * Actualiza la información de un usuario existente.
     *
     * @param pEntity Usuario con la información actualizada.
     * @param token   Token de autenticación.
     * @return ResponseEntity sin contenido si la actualización fue exitosa, o ResponseEntity con estado de error no autorizado si el token no es válido.
     */
    @PutMapping(value = "/updateEntry", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateUser(@RequestBody User pEntity, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        String prefix = "Bearer ";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        pEntity.setPassword(passwordEncoder.encode(pEntity.getPassword()));
        service.updateEntry(pEntity);
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * Elimina un usuario existente.
     *
     * @param pId   ID del usuario a eliminar.
     * @param token Token de autenticación.
     * @return ResponseEntity sin contenido si la eliminación fue exitosa, o ResponseEntity con estado de error no autorizado si el token no es válido.
     */
    @DeleteMapping(value = "/deleteEntry/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long pId, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        String prefix = "Bearer ";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.deleteEntry(pId);
        return ResponseEntity.ok().build();
    }
    

    /**
     * Obtiene una página de usuarios.
     *
     * @param page   Número de página.
     * @param size   Tamaño de la página.
     * @param order  Campo utilizado para ordenar la lista de usuarios.
     * @param asc    Indica si el orden es ascendente o descendente.
     * @param token  Token de autenticación.
     * @return ResponseEntity con la página de usuarios si el token es válido, o ResponseEntity con estado de error no autorizado si el token no es válido.
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<User>> paginas (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order, @RequestParam(defaultValue = "true") boolean asc, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        String prefix = "Bearer ";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    
        Page<User> users = service.paginas(
            PageRequest.of(page, size, Sort.by(order))
        );
    
        if (!asc)
            users = service.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
        System.out.println(users);
        return new ResponseEntity<Page<User>>(users, HttpStatus.OK);
    }
    

    /**
     * Verifica si un token es válido.
     *
     * @param token Token a validar.
     * @return true si el token es válido, false de lo contrario.
     */
    private boolean isValidToken(String token) {
        try {
            String url = "http://localhost:11234/api/v1/auth/validate-token";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(new TokenValidationRequest(token));

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<TokenValidationResponse> responseEntity = new RestTemplate().exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    TokenValidationResponse.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                TokenValidationResponse response = responseEntity.getBody();
                return response != null && response.isValid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
