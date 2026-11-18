package com.example.NutreAPP.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.repositories.IVideo;

@Service
public class VisualizacionServicio {


    @Autowired
    private IVideo repositorio;

   
    public Video guardar(Video datos) {
        return this.repositorio.save(datos);
    }

    //metodo para buscar por id
       public Visualizacion buscarPorId(UUID id){
          Optional<Vizualizacion> visualizacionBuscado = this.repositorio.findById(id);
          if(!visualizacionBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            return visualizacionBuscado.get();
        
          }
       }

       //metodo para buscar todos
       public List<Visualizacion> buscarTodos(){
        return this.repositorio.findAll();
       }

       public boolean eliminar(UUID id){
        Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
          if(!usuarioBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            this.repositorio.deleteById(id);
            return true;
          }
      
   }


