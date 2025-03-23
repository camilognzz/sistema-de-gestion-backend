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
    private VoluntarioRepository voluntarioRepository;

    public List<Voluntario> getVoluntarios() {
        return voluntarioRepository.findAll();
    }

    public Optional<Voluntario> getVoluntario(Long id) {
        return voluntarioRepository.findById(id);
    }

    public Voluntario createVoluntario(Voluntario voluntario) {
        // Para creación, el ID debe ser null (lo genera la BD)
        if (voluntario.getId() != null) {
            voluntario.setId(null);
        }
        // Aquí podrías agregar validaciones adicionales si es necesario
        return voluntarioRepository.save(voluntario);
    }

    public Voluntario updateVoluntario(Voluntario voluntario) {
        // Verificamos que el voluntario exista antes de actualizar
        if (voluntario.getId() == null || !voluntarioRepository.existsById(voluntario.getId())) {
            return null;
        }
        // Aquí podrías agregar validaciones adicionales si es necesario
        return voluntarioRepository.save(voluntario);
    }

    public void delete(Long id) {
        voluntarioRepository.deleteById(id);
    }
}