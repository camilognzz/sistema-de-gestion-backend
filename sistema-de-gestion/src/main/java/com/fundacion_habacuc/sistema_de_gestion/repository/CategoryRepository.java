package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<TransactionCategory, Long> {
    Optional<TransactionCategory> findByName(String name);
    boolean existsByName(String name);
}