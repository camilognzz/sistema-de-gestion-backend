package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.entity.Proyecto;
import com.fundacion_habacuc.sistema_de_gestion.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getProyecto(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto createProyecto(Proyecto proyecto) {
        // Para creación, el ID debe ser null (lo genera la BD)
        if (proyecto.getId() != null) {
            proyecto.setId(null);
        }
        // Validación adicional si es necesaria (por ejemplo, fechas)
        if (proyecto.getFechaFin().isBefore(proyecto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        return proyectoRepository.save(proyecto);
    }

    public Proyecto updateProyecto(Proyecto proyecto) {
        // Verificamos que el proyecto exista
        if (proyecto.getId() == null || !proyectoRepository.existsById(proyecto.getId())) {
            return null;
        }
        // Validación adicional si es necesaria
        if (proyecto.getFechaFin().isBefore(proyecto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        return proyectoRepository.save(proyecto);
    }

    public void delete(Long id) {
        proyectoRepository.deleteById(id);
    }
}