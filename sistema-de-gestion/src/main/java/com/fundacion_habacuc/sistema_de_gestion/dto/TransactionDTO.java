package com.fundacion_habacuc.sistema_de_gestion.dto;

import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private String description;
    private Double amount;
    private TransactionType type;
    private Long categoryId;
    private LocalDate transactionDate;
}