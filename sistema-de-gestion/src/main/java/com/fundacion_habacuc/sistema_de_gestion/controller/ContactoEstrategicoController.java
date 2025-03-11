package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.ContactoEstrategico;
import com.fundacion_habacuc.sistema_de_gestion.service.ContactoEstrategicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contactos-estrategicos")
public class ContactoEstrategicoController {

    @Autowired
    private ContactoEstrategicoService contactoEstrategicoService;

    @GetMapping
    public List<ContactoEstrategico> getAll(){
        return contactoEstrategicoService.getContactosEstrategicos();
    }

    @GetMapping("/{id}")
    public Optional<ContactoEstrategico> getById(@PathVariable("id") Long id){
        return contactoEstrategicoService.getContactoEstrategico(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody ContactoEstrategico contactoEstrategico){
        contactoEstrategicoService.savOrUpdate(contactoEstrategico);
    }

    @DeleteMapping("/{id}")
    public void getAll(@PathVariable("id") Long id){
        contactoEstrategicoService.delete(id);
    }
}
