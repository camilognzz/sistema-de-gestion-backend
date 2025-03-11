package com.fundacion_habacuc.sistema_de_gestion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contactos_estrategicos")
public class ContactoEstrategico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContacto tipoContacto;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(length = 255)
    private String direccion;

    @Column(length = 50)
    private String cargo; // Solo si es un contacto empresarial

    @Column(columnDefinition = "TEXT")
    private String notas; // Notas adicionales

    @Column(nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();
}
