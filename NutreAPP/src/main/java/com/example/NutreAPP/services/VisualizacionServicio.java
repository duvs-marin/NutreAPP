package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Visualizacion;
import com.example.NutreAPP.repositories.IVisualizacion;

@Service
public class VisualizacionServicio {

    @Autowired
    private IVisualizacion repositorio;

    // Método para guardar una visualización
    public Visualizacion guardar(Visualizacion datos) {
        return this.repositorio.save(datos);
    }

    // Método para buscar por id
    public Visualizacion buscarPorId(UUID id) {
        Optional<Visualizacion> visualizacionBuscada = this.repositorio.findById(id);
        if (!visualizacionBuscada.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            return visualizacionBuscada.get();
        }
    }

    // Método para buscar todos
    public List<Visualizacion> buscarTodos() {
        return this.repositorio.findAll();
    }

    // Método para eliminar
    public boolean eliminar(UUID id) {
        Optional<Visualizacion> visualizacionBuscada = this.repositorio.findById(id);
        if (!visualizacionBuscada.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        } else {
            this.repositorio.deleteById(id);
            return true;
        }
    }
}