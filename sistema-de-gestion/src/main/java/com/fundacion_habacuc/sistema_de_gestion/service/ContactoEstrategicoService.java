package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.dto.ContactoEstrategicoDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.ContactoEstrategico;
import com.fundacion_habacuc.sistema_de_gestion.exception.ContactoNoEncontradoException;
import com.fundacion_habacuc.sistema_de_gestion.repository.ContactoEstrategicoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactoEstrategicoService {
    private final ContactoEstrategicoRepository contactoRepository;
    private final ModelMapper modelMapper; // Inyectamos ModelMapper para convertir objetos

    public List<ContactoEstrategicoDTO> obtenerContactos() {
        return contactoRepository.findAll().stream()
                .map(contacto -> modelMapper.map(contacto, ContactoEstrategicoDTO.class))
                .collect(Collectors.toList());
    }

    public ContactoEstrategicoDTO agregarContacto(ContactoEstrategico contacto) {
        ContactoEstrategico nuevoContacto = contactoRepository.save(contacto);
        return modelMapper.map(nuevoContacto, ContactoEstrategicoDTO.class);
    }

    public void eliminarContacto(Long id) {
        Optional<ContactoEstrategico> contacto = contactoRepository.findById(id);
        if (contacto.isEmpty()) {
            throw new ContactoNoEncontradoException("Contacto con ID " + id + " no encontrado");
        }
        contactoRepository.deleteById(id);
    }
}
