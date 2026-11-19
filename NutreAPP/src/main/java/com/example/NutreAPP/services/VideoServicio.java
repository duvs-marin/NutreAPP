package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideo;

@Service
public class VideoServicio {

    // INYECCIÓN DE DEPENDENCIAS
    @Autowired
    private IVideo repositorio;

    // Método para guardar un video
    public Video guardar(Video datos) {
        return this.repositorio.save(datos);
    }

    // Método para buscar por id
    public Video buscarPorId(UUID id) {
        Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            return videoBuscado.get();
        }
    }

    // Método para buscar todos
    public List<Video> buscarTodos() {
        return this.repositorio.findAll();
    }

    // Método para actualizar (modificar)
    public Video modificar(UUID id, Video datos) {
        Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            // 1. Precargar la info que existe actualmente en BD
            Video videoEncontrado = videoBuscado.get();

            // 2. Modificar solo las propiedades permitidas de Video
            videoEncontrado.setTitulo(datos.getTitulo());
            videoEncontrado.setCategoria(datos.getCategoria());

            // 3. Guardar y devolver el video modificado
            return this.repositorio.save(videoEncontrado);
        }
    }

    // Método para eliminar
    public boolean eliminar(UUID id) {
        Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            this.repositorio.deleteById(id);
            return true;
        }
    }
}