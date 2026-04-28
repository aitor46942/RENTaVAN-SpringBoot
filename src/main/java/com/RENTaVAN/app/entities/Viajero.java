package com.RENTaVAN.app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "viajeros")
@Data
public class Viajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viajero")
    private Long idViajero;

    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "dni_pasaporte", length = 50)
    private String dniPasaporte;
}
