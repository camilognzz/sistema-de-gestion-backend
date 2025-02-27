package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}