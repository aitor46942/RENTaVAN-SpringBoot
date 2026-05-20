package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.AuthResponse;
import com.RENTaVAN.app.dto.AuthResponseDTO;
import com.RENTaVAN.app.dto.LoginDTO;
import com.RENTaVAN.app.dto.UsuarioRegistroDTO;
import com.RENTaVAN.app.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    // POST /api/auth/login
    // Recibe: { "email": "...", "password": "..." }
    // Devuelve: { "exito": true, "idUsuario": 1, "nombre": "Aitor", ... }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {
        AuthResponseDTO respuesta = usuarioService.autenticar(loginDto);
        if (respuesta.isExito()) {
            return ResponseEntity.ok(respuesta);
        }
        // 401 cuando las credenciales son incorrectas
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }


    // POST /api/auth/register
    // Recibe: { "nombre": "...", "email": "...", "password": "...", "telefono": "..." }
    // CORRECCIÓN: antes devolvía hardcoded "Registro exitoso" sin tocar la BD
    // Ahora llama al servicio real que guarda el usuario con BCrypt
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody UsuarioRegistroDTO registroDTO) {
        AuthResponseDTO respuesta = usuarioService.registrar(registroDTO);
        if (respuesta.isExito()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        return ResponseEntity.badRequest().body(respuesta);
    }

//    @PostMapping("/register") // Combinado con lo anterior forma /api/auth/register
//    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroDTO registroDTO) {
//        // Tu lógica de registro y BCrypt aquí
//        return ResponseEntity.ok(new AuthResponse(true, "Registro exitoso"));
//    }
}