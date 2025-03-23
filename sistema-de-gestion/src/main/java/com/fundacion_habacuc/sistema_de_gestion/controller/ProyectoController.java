package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.Proyecto;
import com.fundacion_habacuc.sistema_de_gestion.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> getAll() {
        return ResponseEntity.ok(proyectoService.getProyectos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") Long id) {
        Optional<Proyecto> proyecto = proyectoService.getProyecto(id);
        return proyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto proyecto) {
        Proyecto createdProyecto = proyectoService.createProyecto(proyecto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> updateProyecto(@PathVariable("id") Long id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id); // Aseguramos que el ID coincida con la URL
        Proyecto updatedProyecto = proyectoService.updateProyecto(proyecto);
        return updatedProyecto != null ? ResponseEntity.ok(updatedProyecto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable("id") Long id) {
        proyectoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}