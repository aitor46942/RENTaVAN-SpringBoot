package com.RENTaVAN.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private boolean exito;
    private String mensaje;
    private Long idUsuario;
    private String nombre;
}