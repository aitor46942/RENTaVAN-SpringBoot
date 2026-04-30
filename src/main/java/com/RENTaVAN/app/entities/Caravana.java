package com.RENTaVAN.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "caravanas")
@Data
public class Caravana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caravana")
    private Long idCaravana;

    @Column(nullable = false)
    private String modelo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonBackReference // Evita que la caravana vuelva a serializar al propietario
    private Usuario propietario;
}