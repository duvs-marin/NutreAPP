package com.example.NutreAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;//

import com.example.NutreAPP.repositories.IUsuarioRepositorio;   

@Service
public class UsuasioServicio{
    
        //para cocinar necesito las recetas
        //para progrmar los servicios necesito los repositorios 

        //INYECCION DE DEPENDENCIAS
        @Autowired
        private IUsuarioRepositorio repositorio;

       //metodo para guardar un usuario
       public Usuario guardar(Usuario datos){
        return this.repositorio.save(datos);
       }


    }

    
