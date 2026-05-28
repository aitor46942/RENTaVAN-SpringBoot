package com.RENTaVAN.app.dto;

import lombok.Data;

@Data
public class AlquilerResponseDTO {
    private Long   idAlquiler;
    private Long   idCaravana;
    private String modeloCaravana;
    private Long   idCliente;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
}
