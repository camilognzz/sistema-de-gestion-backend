package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.dto.ProyectoDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.Proyecto;
import com.fundacion_habacuc.sistema_de_gestion.entity.Usuario;
import com.fundacion_habacuc.sistema_de_gestion.exception.UsuarioNoEncontradoException;
import com.fundacion_habacuc.sistema_de_gestion.repository.ProyectoRepository;
import com.fundacion_habacuc.sistema_de_gestion.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper; // Inyectamos ModelMapper para convertir objetos

    public List<ProyectoDTO> obtenerProyectos() {
        return proyectoRepository.findAll().stream()
                .map(proyecto -> modelMapper.map(proyecto, ProyectoDTO.class))
                .collect(Collectors.toList());
    }

    public ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO) {
        Usuario encargado = usuarioRepository.findById(proyectoDTO.getEncargadoId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + proyectoDTO.getEncargadoId() + " no encontrado"));

        Proyecto proyecto = modelMapper.map(proyectoDTO, Proyecto.class);
        proyecto.setEncargado(encargado);

        Proyecto nuevoProyecto = proyectoRepository.save(proyecto);
        return modelMapper.map(nuevoProyecto, ProyectoDTO.class);
    }
}
