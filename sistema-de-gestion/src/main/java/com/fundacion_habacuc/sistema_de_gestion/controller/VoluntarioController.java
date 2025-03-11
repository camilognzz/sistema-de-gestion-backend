package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.Student;
import com.fundacion_habacuc.sistema_de_gestion.entity.Voluntario;
import com.fundacion_habacuc.sistema_de_gestion.service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/voluntarios")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping
    public List<Voluntario> getAll(){
        return voluntarioService.getVoluntarios();
    }

    @GetMapping("/{id}")
    public Optional<Voluntario> getById(@PathVariable("id") Long id){
        return voluntarioService.getVoluntario(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Voluntario voluntario){
        voluntarioService.savOrUpdate(voluntario);
    }

    @DeleteMapping("/{id}")
    public void getAll(@PathVariable("id") Long id){
        voluntarioService.delete(id);
    }
}
