package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Ususario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.validations.UsuarioValidacion;

@Service
public class UsuasioServicio {

    // INYECCION DE DEPENDENCIAS
    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private UsuarioValidacion validador;

    // Método para guardar un usuario
    public Ususario guardar(Ususario datos) {
        this.validador.validarCorreo(datos.getCorreo());
        this.validador.validarUsuarioNuevo(datos);
        this.validador.validarNombre(datos.getNombre());
        this.validador.validarContraseña(datos.getContraseña_hash());
        return this.repositorio.save(datos);
    }

    // Método para buscar por id
    public Ususario buscarPorId(UUID id) {
        Optional<Ususario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            return usuarioBuscado.get();
        }
    }

    // Método para buscar todos
    public List<Ususario> buscarTodos() {
        return this.repositorio.findAll();
    }

    // Método para actualizar (modificar)
    public Ususario modificar(UUID id, Ususario datos) {
        Optional<Ususario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            // 1. Precargar la info que existe actualmente en BD
            Ususario usuarioEncontrado = usuarioBuscado.get();

            // 2. Modificar solo las propiedades permitidas
            usuarioEncontrado.setNombre(datos.getNombre());
            usuarioEncontrado.setRol(datos.getRol());

            // 3. Guardar y devolver el usuario modificado
            return this.repositorio.save(usuarioEncontrado);
        }
    }

    // Método para eliminar
    public boolean eliminar(UUID id) {
        Optional<Ususario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            this.repositorio.deleteById(id);
            return true;
        }
    }
}