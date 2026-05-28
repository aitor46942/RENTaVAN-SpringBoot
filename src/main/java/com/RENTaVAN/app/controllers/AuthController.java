package com.RENTaVAN.app.controllers;

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

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {
        AuthResponseDTO respuesta = usuarioService.autenticar(loginDto);
        if (respuesta.isExito()) {
            return ResponseEntity.ok(respuesta);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody UsuarioRegistroDTO registroDTO) {
        AuthResponseDTO respuesta = usuarioService.registrar(registroDTO);
        if (respuesta.isExito()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        return ResponseEntity.badRequest().body(respuesta);
    }
}