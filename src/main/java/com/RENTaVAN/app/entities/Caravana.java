package com.RENTaVAN.app.entities;

import jakarta.persistence.*;
import lombok.Data;
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

    // Este campo se conecta con el 'location GEOMETRY'
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario;
}