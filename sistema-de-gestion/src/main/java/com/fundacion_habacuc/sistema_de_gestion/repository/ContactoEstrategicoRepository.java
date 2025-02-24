package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.ContactoEstrategicoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoEstrategicoRepository extends JpaRepository<ContactoEstrategicoDTO, Long> {
    Optional<ContactoEstrategicoDTO> findByEmail(String email);
}
