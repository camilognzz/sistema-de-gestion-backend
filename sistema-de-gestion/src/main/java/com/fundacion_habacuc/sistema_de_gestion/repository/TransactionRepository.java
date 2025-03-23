package com.fundacion_habacuc.sistema_de_gestion.repository;

import com.fundacion_habacuc.sistema_de_gestion.entity.FinancialTransaction;
import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    List<FinancialTransaction> findByTransactionDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT SUM(t.amount) FROM FinancialTransaction t WHERE t.type = :type")
    Double sumByType(@Param("type") TransactionType type);
}