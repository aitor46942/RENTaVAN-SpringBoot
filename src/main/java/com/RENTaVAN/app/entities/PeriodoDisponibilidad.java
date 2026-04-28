package com.RENTaVAN.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "periodos_disponibilidad")
@Data
public class PeriodoDisponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long idPeriodo;

    @ManyToOne
    @JoinColumn(name = "id_caravana", nullable = false)
    private Caravana caravana;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
}