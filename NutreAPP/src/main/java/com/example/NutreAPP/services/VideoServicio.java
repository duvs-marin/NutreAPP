package com.example.NutreAPP.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    //metodo para buscar por id
       public Video buscarPorId(UUID id){
          Optional<Video> videoBuscado = this.repositorio.findById(id);
          if(!videoBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            return videoBuscado.get();
        
          }
       }

       //metodo para buscar todos
       public List<Video> buscarTodos(){
        return this.repositorio.findAll();
       }

       //metodo para actulizar(modificar)
       public Video modificar(UUID id, Video datos){
          if(!VideoBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            //1.PRECARGAR LA INFO QUE EXISTE ACTUALMENTE EN BD
            Video videoEncontrado=videoBuscado.get();

            //2.espacio para modificar solo las propiedades permitidos
            videoEncontrado.setNombre(datos.getNombre());
            videoEncontrado.setRol(datos.getRol());

            //3.espacio para volver a guardar el usuario que se acaba de modficar
            this.repositorio.save(videoEncontrado);

          }
}