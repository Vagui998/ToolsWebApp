package com.javeriana.tool_manager.Controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.Services.ToolCrudService;


/**
 * Clase controladora que maneja las solicitudes relacionadas con la entidad tool.
 */
@RestController
@RequestMapping("/tools")
@CrossOrigin(origins = "http://localhost:4200")
public class ToolCrudController
{    

    @Autowired
    protected ToolCrudService service;

      /**
     * Obtiene todas las herramientas existentes.
     *
     * @return Una lista de objetos Tool.
     */
    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Tool> getAllTools()
    {
        return service.getAll();
    }

    /**
     * Obtiene una herramienta por su ID.
     *
     * @param pId El ID de la herramienta.
     * @return La herramienta correspondiente al ID especificado.
     */
    @GetMapping(value = "/{id}")
    public Tool getToolById(@PathVariable(value = "id") Long pId)
    {
        return service.getByID(pId);
    }


     /**
     * Crea una nueva herramienta.
     *
     * @param pEntity La herramienta a crear.
     * @param token   El token de autenticación.
     * @return ResponseEntity con el estado de la respuesta.
     */
    @PostMapping(value = "/newEntry")
    public ResponseEntity<?> newTool(@RequestBody Tool pEntity, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        String prefix = "Bearer ";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.newEntry(pEntity);
        return ResponseEntity.ok().build();
    }
    
     /**
     * Actualiza una herramienta existente.
     *
     * @param pEntity La herramienta a actualizar.
     * @param token   El token de autenticación.
     * @return ResponseEntity con el estado de la respuesta.
     */
    @PutMapping(value = "/updateEntry", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateTool(@RequestBody Tool pEntity, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        String prefix = "Bearer ";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.updateEntry(pEntity);
        return ResponseEntity.ok().build();
    }
    
     /**
     * Elimina una herramienta existente por su ID.
     *
     * @param pId     El ID de la herramienta a eliminar.
     * @param token   El token de autenticación.
     * @return ResponseEntity con el estado de la respuesta.
     */
    @DeleteMapping(value = "/deleteEntry/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable(value = "id") Long pId, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
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
     * Obtiene las herramientas con paginación y ordenamiento.
     *
     * @param page   El número de página.
     * @param size   El tamaño de la página.
     * @param order  El orden de los resultados.
     * @param asc    Indica si el orden es ascendente o descendente.
     * @param token  El token de autenticación.
     * @return ResponseEntity con la página de herramientas y el estado de la respuesta.
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<Tool>> paginas (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order, @RequestParam(defaultValue = "true") boolean asc, @RequestHeader(value = "Authorization", defaultValue = "") String token) {

        Page<Tool> tools = service.paginas(
            PageRequest.of(page, size, Sort.by(order))
        );

        if (!asc)
            tools = service.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
        
        return new ResponseEntity<Page<Tool>>(tools, HttpStatus.OK);
    }

     /**
     * Valida si un token de autenticación es válido.
     *
     * @param token El token de autenticación a validar.
     * @return true si el token es válido, false en caso contrario.
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
