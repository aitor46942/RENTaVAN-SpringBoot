package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.AuthResponseDTO;
import com.RENTaVAN.app.dto.UsuarioRegistroDTO;
import com.RENTaVAN.app.dto.UsuarioResponseDTO;
import com.RENTaVAN.app.dto.UsuarioUpdateDTO;
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

    @PostMapping
    public AuthResponseDTO crear(@RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.registrar(dto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }
}