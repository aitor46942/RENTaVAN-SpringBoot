package com.RENTaVAN.app.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private String nombre;
    private String email;
    private String password; // Solo se recibe aquí para ser hasheada en el Service
    private String telefono;
}