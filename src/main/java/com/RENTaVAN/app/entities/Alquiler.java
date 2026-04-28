package com.RENTaVAN.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "alquileres")
@Data
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Long idAlquiler;

    @ManyToOne
    @JoinColumn(name = "id_caravana", nullable = false)
    private Caravana caravana;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(length = 50)
    private String estado; // Ej: 'Pendiente', 'Confirmado'

    // Relación con los viajeros que participan en este alquiler
    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL)
    private List<Viajero> viajeros;
}