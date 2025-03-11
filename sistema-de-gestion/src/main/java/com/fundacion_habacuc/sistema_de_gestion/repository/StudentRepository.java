package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
