package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.Proyecto;
import com.fundacion_habacuc.sistema_de_gestion.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public List<Proyecto> getAll(){
        return proyectoService.getProyectos();
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> getById(@PathVariable("id") Long id){
        return proyectoService.getProyecto(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Proyecto proyecto){
        proyectoService.savOrUpdate(proyecto);
    }

    @DeleteMapping("/{id}")
    public void getAll(@PathVariable("id") Long id){
        proyectoService.delete(id);
    }
}
