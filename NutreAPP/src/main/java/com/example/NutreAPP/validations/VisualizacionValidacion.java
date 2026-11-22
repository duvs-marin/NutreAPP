package com.example.NutreAPP.validations;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Ususario;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.models.Visualizacion;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.repositories.IVideo;

@Component
public class VisualizacionValidacion {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IVideo videoRepositorio;

    // V1 y V2. Fecha de visualización obligatoria y no puede ser futura -> 400
    public void validarFechaVisualizacion(LocalDate fecha) {
        if (fecha == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo fecha_visualizacion es obligatorio");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la fecha de visualizacion no puede ser futura");
        }
    }

    // V3. El campo completado es obligatorio (true o false, no null) -> 400
    public void validarCompletado(Boolean completado) {
        if (completado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el campo completado es obligatorio");
        }
    }

    // V4. El usuario y el video deben EXISTIR en la BD -> 404
    public void validarUsuarioYVideoExisten(Ususario ususario, Video video) {
        if (ususario == null || ususario.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "el usuario no existe en la base de datos");
        }
        UUID idUsuario = ususario.getId();
        if (!this.usuarioRepositorio.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "el usuario con el id indicado no existe en la BD");
        }

        if (video == null || video.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "el video no existe en la base de datos");
        }
        UUID idVideo = video.getId();
        if (!this.videoRepositorio.existsById(idVideo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "el video con el id indicado no existe en la BD");
        }
    }

    // Validación completa para una Visualizacion
    public void validarVisualizacion(Visualizacion datos) {
        validarFechaVisualizacion(datos.getFecha_visualizacion());
        validarCompletado(datos.getCompletado());
        validarUsuarioYVideoExisten(datos.getUsusario(), datos.getVideo());
    }
}