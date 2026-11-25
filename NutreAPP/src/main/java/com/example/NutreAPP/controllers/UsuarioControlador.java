package com.example.NutreAPP.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NutreAPP.models.Ususario;
import com.example.NutreAPP.services.UsuasioServicio;

@RestController
@RequestMapping("/api/v1/ususarios")
public class UsuarioControlador {

   @Autowired
   private UsuasioServicio servivcio;

   @PostMapping
   public ResponseEntity<Ususario> guardar(@RequestBody Ususario datos){
    return ResponseEntity.status(HttpStatus.CREATED).body(this.servivcio.guardar(datos));
   }

   @GetMapping
   public ResponseEntity<List<Ususario>> buscarTodos(){
    return ResponseEntity.status(HttpStatus.OK).body(this.servivcio.buscarTodos());
   }

   @GetMapping ("/{id}")
   public ResponseEntity<Ususario> buscarPorId(@PathVariable UUID id){
    return ResponseEntity.status(HttpStatus.OK).body(this.servivcio.buscarPorId(id));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Ususario> modificar(@PathVariable UUID id, @RequestBody Ususario datos){
    return ResponseEntity.status(HttpStatus.OK).body(this.servivcio.modificar(id,datos));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Boolean> eliminar(@PathVariable UUID id){
    return ResponseEntity.status(HttpStatus.OK).body(this.servivcio.eliminar (id));
   }

}
