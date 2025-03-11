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
    ProyectoRepository proyectoRepository;

    public List<Proyecto> getProyectos(){
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getProyecto(Long id){
        return proyectoRepository.findById(id);
    }

    public void savOrUpdate(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

    public void delete(Long id){
        proyectoRepository.deleteById(id);
    }
}
