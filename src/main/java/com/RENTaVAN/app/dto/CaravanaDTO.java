package com.RENTaVAN.app.dto;

import lombok.Data;

@Data
public class CaravanaDTO {
    private String modelo;
    private String descripcion;
    private Long idPropietario;
    private Double latitud;
    private Double longitud;
}