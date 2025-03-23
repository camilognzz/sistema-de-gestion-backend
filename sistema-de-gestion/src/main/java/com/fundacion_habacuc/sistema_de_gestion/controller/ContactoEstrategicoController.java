package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.ContactoEstrategico;
import com.fundacion_habacuc.sistema_de_gestion.service.ContactoEstrategicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contactos-estrategicos")
public class ContactoEstrategicoController {

    @Autowired
    private ContactoEstrategicoService contactoEstrategicoService;

    @GetMapping
    public ResponseEntity<List<ContactoEstrategico>> getAll() {
        return ResponseEntity.ok(contactoEstrategicoService.getContactosEstrategicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoEstrategico> getById(@PathVariable("id") Long id) {
        Optional<ContactoEstrategico> contacto = contactoEstrategicoService.getContactoEstrategico(id);
        return contacto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactoEstrategico> createContactoEstrategico(@RequestBody ContactoEstrategico contactoEstrategico) {
        ContactoEstrategico createdContacto = contactoEstrategicoService.createContactoEstrategico(contactoEstrategico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoEstrategico> updateContactoEstrategico(@PathVariable("id") Long id, @RequestBody ContactoEstrategico contactoEstrategico) {
        contactoEstrategico.setId(id); // Aseguramos que el ID coincida con la URL
        ContactoEstrategico updatedContacto = contactoEstrategicoService.updateContactoEstrategico(contactoEstrategico);
        return updatedContacto != null ? ResponseEntity.ok(updatedContacto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactoEstrategico(@PathVariable("id") Long id) {
        contactoEstrategicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}