package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.entity.Voluntario;
import com.fundacion_habacuc.sistema_de_gestion.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {
    @Autowired
    VoluntarioRepository voluntarioRepository;

    public List<Voluntario> getVoluntarios(){
        return voluntarioRepository.findAll();
    }

    public Optional<Voluntario> getVoluntario(Long id){
        return voluntarioRepository.findById(id);
    }

    public void savOrUpdate(Voluntario voluntario){
        voluntarioRepository.save(voluntario);
    }

    public void delete(Long id){
        voluntarioRepository.deleteById(id);
    }
}
