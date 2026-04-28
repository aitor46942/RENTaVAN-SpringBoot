package com.RENTaVAN.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario; //[cite: 2]

    @Column(nullable = false)
    private String nombre; //[cite: 2]

    @Column(nullable = false, unique = true)
    private String email; //[cite: 2]

    @Column(nullable = false)
    private String password; //[cite: 2]

    private String telefono; //[cite: 2]

    // Relación: Un usuario puede tener muchas caravanas[cite: 2]
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    private List<Caravana> caravanas;
}