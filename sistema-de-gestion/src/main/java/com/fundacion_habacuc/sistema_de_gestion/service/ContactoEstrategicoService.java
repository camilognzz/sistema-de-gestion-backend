package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.entity.ContactoEstrategico;
import com.fundacion_habacuc.sistema_de_gestion.repository.ContactoEstrategicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoEstrategicoService {

    @Autowired
    private ContactoEstrategicoRepository contactoEstrategicoRepository;

    public List<ContactoEstrategico> getContactosEstrategicos() {
        return contactoEstrategicoRepository.findAll();
    }

    public Optional<ContactoEstrategico> getContactoEstrategico(Long id) {
        return contactoEstrategicoRepository.findById(id);
    }

    public ContactoEstrategico createContactoEstrategico(ContactoEstrategico contactoEstrategico) {
        // Para creación, el ID debe ser null (lo genera la BD)
        if (contactoEstrategico.getId() != null) {
            contactoEstrategico.setId(null);
        }
        // Aquí podrías agregar validaciones adicionales si es necesario
        return contactoEstrategicoRepository.save(contactoEstrategico);
    }

    public ContactoEstrategico updateContactoEstrategico(ContactoEstrategico contactoEstrategico) {
        // Verificamos que el contacto exista
        if (contactoEstrategico.getId() == null || !contactoEstrategicoRepository.existsById(contactoEstrategico.getId())) {
            return null;
        }
        // Aquí podrías agregar validaciones adicionales si es necesario
        return contactoEstrategicoRepository.save(contactoEstrategico);
    }

    public void delete(Long id) {
        contactoEstrategicoRepository.deleteById(id);
    }
}