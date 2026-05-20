package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.AuthResponseDTO;
import com.RENTaVAN.app.dto.UsuarioRegistroDTO;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    //CORRECCIÓN: antes llamaba a registrarUsuario(Usuario)
    // que guardaba sin BCrypt y que ya no existe en el servicio.
    // Ahora recibe un UsuarioRegistroDTO y llama a registrar() que sí usa BCrypt
    @PostMapping
    public AuthResponseDTO crear(@RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.registrar(dto);
    }
}