package com.RENTaVAN.app.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    // CORRECCIÓN: se llamaba 'contrasena' pero Android envía 'password'
    // Al no coincidir el nombre del campo, BCrypt recibía null y el login
    // siempre fallaba aunque la contraseña fuera correcta
    private String password;
}
