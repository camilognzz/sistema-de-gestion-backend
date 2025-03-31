package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.dto.TransactionDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.FinancialTransaction;
import com.fundacion_habacuc.sistema_de_gestion.service.FinancialService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/financial")
@RequiredArgsConstructor
public class FinancialController {
    private final FinancialService financialService;

    @PostMapping("/transactions")
    public ResponseEntity<FinancialTransaction> createTransaction(@Validated @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(financialService.createTransaction(transactionDTO));
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<FinancialTransaction> updateTransaction(
            @PathVariable Long id,
            @Validated @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(financialService.updateTransaction(id, transactionDTO));
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        financialService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getCurrentBalance() {
        return ResponseEntity.ok(financialService.getCurrentBalance());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<FinancialTransaction>> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(financialService.getTransactionsByPeriod(start, end));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<FinancialTransaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(financialService.getTransactionById(id));
    }
}