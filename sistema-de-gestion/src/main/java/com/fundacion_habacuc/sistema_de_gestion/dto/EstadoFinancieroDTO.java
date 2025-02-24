package com.fundacion_habacuc.sistema_de_gestion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoFinancieroDTO {
    private Long id;

    @NotNull(message = "Los ingresos no pueden ser nulos")
    @PositiveOrZero(message = "Los ingresos no pueden ser negativos")
    private Double ingresos;

    @NotNull(message = "Los egresos no pueden ser nulos")
    @PositiveOrZero(message = "Los egresos no pueden ser negativos")
    private Double egresos;

    @NotNull(message = "El balance no puede ser nulo")
    @PositiveOrZero(message = "El balance no puede ser negativo")
    private Double balance;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;
}
