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
    ContactoEstrategicoRepository contactoEstrategicoRepository;

    public List<ContactoEstrategico> getContactosEstrategicos(){
        return contactoEstrategicoRepository.findAll();
    }

    public Optional<ContactoEstrategico> getContactoEstrategico(Long id){
        return contactoEstrategicoRepository.findById(id);
    }

    public void savOrUpdate(ContactoEstrategico contactoEstrategico){
        contactoEstrategicoRepository.save(contactoEstrategico);
    }

    public void delete(Long id){
        contactoEstrategicoRepository.deleteById(id);
    }
}
