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

       //metodo para actulizar(modificar)
       public Visualizacion modificar(UUID id, Visualizacion datos){
          if(!visualizacionBuscado.isPresent()){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado"); 

          }else{
            //1.PRECARGAR LA INFO QUE EXISTE ACTUALMENTE EN BD
            Visualizacion visuliazacionEncontrado=visualizacionBuscado.get();

            //2.espacio para modificar solo las propiedades permitidos
            visualizacionEncontrado.setNombre(datos.getNombre());
            visualizacionEncontrado.setRol(datos.getRol());

            //3.espacio para volver a guardar el usuario que se acaba de modficar
            this.repositorio.save(visualizacionEncontrado);


      }
   }

}
