package com.example.NutreAPP.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository 
public interface IUsuarioRepositorio extends JpaRepository <IUsuarioRepositorio,UUID>{

    //consultas prefabricadas

    //1:buscar un usuario asociado a un departamento y un rol especifico
    List<UsuarioServicio> findByDepartamentoAndRol(String departamento, String rol);

    Optional<UsuarioServicio> findByCorreo(String correo);

    List<UsuarioServicio> findByRolOrderByNombreAsc(String rol);

    boolean existsByCorreo(String correo);

}
