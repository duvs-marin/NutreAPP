package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;//
import org.springframework.web.server.ResponseStatusException;

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

       //metodo para buscar por id
       public Usuario buscarPorId(UUID id){
          Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
          if(!usuarioBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            return usuarioBuscado.get();
        
          }
       }

       //metodo para buscar todos
       public List<Usuario> buscarTodos(){
        return this.repositorio.findAll();
       }

       //metodo para actulizar(modificar)
       public Usuario modificar(UUID id, Usuario datos){
          if(!usuarioBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            //1.PRECARGAR LA INFO QUE EXISTE ACTUALMENTE EN BD
            Usuario usuarioEncontrado=usuarioBuscado.get();

            //2.espacio para modificar solo las propiedades permitidos
            usuarioEncontrado.setNombre(datos.getNombre());
            usuarioEncontrado.setRol(datos.getRol());

            //3.espacio para volver a guardar el usuario que se acaba de modficar
            this.repositorio.save(usuarioEncontrado);
          }
       }


    }

    
