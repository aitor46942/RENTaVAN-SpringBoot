package com.RENTaVAN.app.services;

import com.RENTaVAN.app.dto.AuthResponseDTO;
import com.RENTaVAN.app.dto.LoginDTO;
import com.RENTaVAN.app.dto.UsuarioRegistroDTO;
import com.RENTaVAN.app.dto.UsuarioResponseDTO;
import com.RENTaVAN.app.dto.UsuarioUpdateDTO;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponseDTO registrar(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return new AuthResponseDTO(false, "El email ya está registrado", null, null);
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(dto.getNombre());
        nuevo.setEmail(dto.getEmail());
        nuevo.setTelefono(dto.getTelefono());
        nuevo.setContrasena(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(nuevo);
        return new AuthResponseDTO(true, "Registro exitoso", guardado.getIdUsuario(), guardado.getNombre());
    }

    public AuthResponseDTO autenticar(LoginDTO loginDto) {
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail()).orElse(null);

        if (usuario == null) {
            return new AuthResponseDTO(false, "Usuario no encontrado", null, null);
        }

        boolean coincide = passwordEncoder.matches(loginDto.getPassword(), usuario.getContrasena());

        if (coincide) {
            return new AuthResponseDTO(true, "Login exitoso", usuario.getIdUsuario(), usuario.getNombre());
        } else {
            return new AuthResponseDTO(false, "Contraseña incorrecta", null, null);
        }
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        return toDTO(usuarioRepository.save(usuario));
    }

    private UsuarioResponseDTO toDTO(Usuario u) {
        return new UsuarioResponseDTO(u.getIdUsuario(), u.getNombre(), u.getEmail(), u.getTelefono());
    }
}