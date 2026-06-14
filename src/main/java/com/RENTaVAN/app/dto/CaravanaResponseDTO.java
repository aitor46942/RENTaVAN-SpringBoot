package com.RENTaVAN.app.dto;

import lombok.Data;
@Data
public class CaravanaResponseDTO {
    private Long idCaravana;
    private String modelo;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String matricula;
    private Double precioPorDia;
    private Integer plazas;
    private PropietarioResumenDTO propietario;

    @Data
    public static class PropietarioResumenDTO {
        private Long idUsuario;
        private String nombre;
        private String telefono;
    }
}