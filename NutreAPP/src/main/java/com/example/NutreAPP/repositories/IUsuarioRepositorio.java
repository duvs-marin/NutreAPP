package com.example.NutreAPP.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreAPP.models.Ususario;
import com.example.NutreAPP.models.helpers.Departamento;
import com.example.NutreAPP.models.helpers.Rol;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Ususario, UUID> {

    // Consultas prefabricadas

    // 1: buscar un usuario asociado a un departamento y un rol específico
    List<Ususario> findByDepartamentoAndRol(Departamento departamento, Rol rol);

    Optional<Ususario> findByCorreo(String correo);

    List<Ususario> findByRolOrderByNombreAsc(String rol);

    boolean existsByCorreo(String correo);
}