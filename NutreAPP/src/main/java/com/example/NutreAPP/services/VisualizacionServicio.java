package com.example.NutreAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}