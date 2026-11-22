package com.example.NutreAPP.validations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideo;

@Component
public class VideoValidacion {

    @Autowired
    private IVideo repositorio;

    // V1. Título obligatorio y entre 5 y 120 caracteres -> 400
    public void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo titulo es obligatorio");
        }
        int longitud = titulo.trim().length();
        if (longitud < 5 || longitud > 120) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el titulo debe tener entre 5 y 120 caracteres");
        }
    }

    // V2. URL obligatoria y debe empezar por "http://" o "https://" -> 400
    public void validarUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo url es obligatorio");
        }
        String urlLimpia = url.trim();
        if (!urlLimpia.startsWith("http://") && !urlLimpia.startsWith("https://")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la url debe empezar por http:// o https://");
        }
    }

    // V3. La categoría es obligatoria (no puede llegar null) -> 400
    public void validarCategoria(Object categoria) {
        if (categoria == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo categoria es obligatorio");
        }
    }

    // V4. La descripción es opcional, pero si viene no puede superar 500 caracteres -> 400
    public void validarDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la descripcion no puede superar los 500 caracteres");
        }
    }

    // V5a. Al CREAR: no pueden existir dos videos con la misma URL -> 409
    public void validarVideoNuevo(Video datos) {
        validarTitulo(datos.getTitulo());
        validarUrl(datos.getUrl());
        validarDescripcion(datos.getDescripcion());

        if (this.repositorio.existsByUrl(datos.getUrl().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ya existe un video registrado con esa url");
        }
    }

    // V5b. Al MODIFICAR: verificar que la URL no pertenezca a otro video -> 409
    public void validarVideoModificado(UUID idVideo, Video datos) {
        validarTitulo(datos.getTitulo());
        validarUrl(datos.getUrl());
        validarDescripcion(datos.getDescripcion());

        // Cambia el nombre del método según cómo lo tengas definido en IVideo:
        // Ejemplo: if (this.repositorio.contarUrlEnOtroVideo(datos.getUrl().trim(), idVideo) > 0)
        if (this.repositorio.existsByUrl(datos.getUrl().trim()) && idVideo == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ya existe otro video con esa url");
        }
    }
}