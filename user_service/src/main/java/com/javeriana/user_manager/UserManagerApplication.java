package com.javeriana.user_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación User Manager.
 */
@SpringBootApplication
public class UserManagerApplication {

    /**
     * Método principal que inicia la aplicación User Manager.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }
}
