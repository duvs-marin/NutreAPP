package com.example.NutreAPP.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreAPP.models.Visualizacion;

@Repository
public interface IVisualizacion extends JpaRepository<Visualizacion, UUID> {

}