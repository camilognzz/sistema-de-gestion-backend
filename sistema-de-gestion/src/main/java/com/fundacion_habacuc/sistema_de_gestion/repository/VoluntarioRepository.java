package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}
