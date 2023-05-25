package com.javeriana.auth_manager.Entities;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToMany;


/**
 * Clase que representa un usuario en el sistema de autenticación.
 * Implementa la interfaz UserDetails de Spring Security para obtener detalles de autenticación y autorización.
 * También implementa la interfaz JsonSerializable de Jackson para la serialización en formato JSON.
 */
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User implements UserDetails, JsonSerializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @JsonProperty("username")
    private String username;

    @Column(name = "pass")
    @JsonProperty("password")
    private String password;

    @Enumerated(EnumType.STRING)
    @JsonProperty("role")
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonProperty("tokens")
    private List<Token> tokens;

    /**
     * Retorna la lista de autoridades/granted authorities asociadas al usuario.
     * En este caso, retorna una única autoridad con el nombre del rol del usuario.
     *
     * @return Lista de autoridades/granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     * En este caso, siempre retorna true, lo que significa que la cuenta no ha expirado.
     *
     * @return true si la cuenta no ha expirado, false de lo contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     * En este caso, siempre retorna true, lo que significa que la cuenta no está bloqueada.
     *
     * @return true si la cuenta no está bloqueada, false de lo contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     * En este caso, siempre retorna true, lo que significa que las credenciales no han expirado.
     *
     * @return true si las credenciales no han expirado, false de lo contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     * En este caso, siempre retorna true, lo que significa que el usuario está habilitado.
     *
     * @return true si el usuario está habilitado, false de lo contrario.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Serializa el objeto User en formato JSON.
     *
     * @param gen         Generador de JSON.
     * @param serializers Proveedor de serializadores.
     * @throws IOException Si ocurre un error durante la serialización.
     */
    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", this.id);
        gen.writeStringField("username", this.username);
        gen.writeStringField("password", this.password);
        gen.writeStringField("role", this.role.name());
        int tokensActivos = 0;
        for (int i = 0; i < this.tokens.size(); i++) {
            if (!tokens.get(i).isExpired() && !tokens.get(i).isRevoked()) {
                gen.writeStringField("access_token" + tokensActivos, this.tokens.get(i).getToken());
                tokensActivos++;
            }
        }
        gen.writeEndObject();
    }

    /**
     * Serializa el objeto User con tipo en formato JSON.
     * Este método simplemente llama al método serialize para realizar la serialización.
     *
     * @param gen          Generador de JSON.
     * @param serializers Proveedor de serializadores.
     * @param typeSer      Serializador de tipo.
     * @throws IOException Si ocurre un error durante la serialización.
     */
    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer)
            throws IOException {
        serialize(gen, serializers);
    }
}
