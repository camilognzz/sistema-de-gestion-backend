package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.dto.TransactionDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.FinancialTransaction;
import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionCategory;
import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionType;
import com.fundacion_habacuc.sistema_de_gestion.repository.CategoryRepository;
import com.fundacion_habacuc.sistema_de_gestion.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public FinancialTransaction createTransaction(TransactionDTO transactionDTO) {
        TransactionCategory category = categoryRepository.findById(transactionDTO.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));

        FinancialTransaction transaction = new FinancialTransaction(
                null, // ID autogenerado
                transactionDTO.getDescription(),
                transactionDTO.getAmount(),
                transactionDTO.getType(),
                category,
                transactionDTO.getTransactionDate()
        );

        return transactionRepository.save(transaction);
    }

    @Transactional
    public FinancialTransaction updateTransaction(Long id, TransactionDTO transactionDTO) {
        FinancialTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacción no encontrada"));

        TransactionCategory category = categoryRepository.findById(transactionDTO.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));

        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setCategory(category);
        transaction.setTransactionDate(transactionDTO.getTransactionDate());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        FinancialTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacción no encontrada"));
        transactionRepository.delete(transaction);
    }

    public Double getCurrentBalance() {
        Double totalIncome = Optional.ofNullable(transactionRepository.sumByType(TransactionType.INCOME)).orElse(0.0);
        Double totalExpense = Optional.ofNullable(transactionRepository.sumByType(TransactionType.EXPENSE)).orElse(0.0);
        return totalIncome - totalExpense;
    }

    public List<FinancialTransaction> getTransactionsByPeriod(LocalDate start, LocalDate end) {
        return transactionRepository.findByTransactionDateBetween(start, end);
    }

    public FinancialTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacción no encontrada"));
    }
}