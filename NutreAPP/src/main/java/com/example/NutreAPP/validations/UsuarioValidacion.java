package com.example.NutreAPP.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Ususario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Component
public class UsuarioValidacion {

    @Autowired
    private IUsuarioRepositorio repositorio;

    // 1. Validar texto obligatorio
    public void validarTextoObligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo " + campo + " es obligatorio");
        }
    }

    // 2. Validar la longitud de un nombre
    public void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 3 || nombre.trim().length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el nombre debe tener entre 3 y 100 caracteres");
        }
    }

    // 3. Validar formato de correo
    public void validarCorreo(String correo) {
        if (correo == null || !correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el correo no cumple el formato esperado");
        }
    }

    // 4. Validar que la contraseña tenga al menos 8 caracteres
    public void validarContraseña(String contraseña) {
        if (contraseña == null || contraseña.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la contraseña debe ser más grande de 8 caracteres");
        }
    }

    // Validación de negocio: validar que el correo no exista en BD
    public void validarUsuarioNuevo(Ususario datos) {
        String correoEntregado = datos.getCorreo();
        if (this.repositorio.existsByCorreo(correoEntregado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el correo entregado ya existe en BD");
        }
    }
}