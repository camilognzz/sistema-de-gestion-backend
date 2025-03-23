package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.Voluntario;
import com.fundacion_habacuc.sistema_de_gestion.service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/voluntarios")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping
    public ResponseEntity<List<Voluntario>> getAll() {
        return ResponseEntity.ok(voluntarioService.getVoluntarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> getById(@PathVariable("id") Long id) {
        Optional<Voluntario> voluntario = voluntarioService.getVoluntario(id);
        return voluntario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Voluntario> createVoluntario(@RequestBody Voluntario voluntario) {
        Voluntario createdVoluntario = voluntarioService.createVoluntario(voluntario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoluntario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> updateVoluntario(@PathVariable("id") Long id, @RequestBody Voluntario voluntario) {
        voluntario.setId(id); // Aseguramos que el ID coincida con la URL
        Voluntario updatedVoluntario = voluntarioService.updateVoluntario(voluntario);
        return updatedVoluntario != null ? ResponseEntity.ok(updatedVoluntario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable("id") Long id) {
        voluntarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}