package com.RENTaVAN.app.services;


import com.RENTaVAN.app.dto.AuthResponseDTO;
import com.RENTaVAN.app.dto.LoginDTO;
import com.RENTaVAN.app.dto.UsuarioRegistroDTO;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Crea el constructor para la inyección automática
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Aquí podrías añadir lógica para encriptar la contraseña
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    private final BCryptPasswordEncoder passwordEncoder;

    public Usuario registrar(UsuarioRegistroDTO dto) {
        // 1. Verificar si el email ya existe para evitar duplicados
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setTelefono(dto.getTelefono());

        // 2. CIFRADO CRÍTICO
        String hash = passwordEncoder.encode(dto.getPassword());
        nuevoUsuario.setContrasena(hash);

        return usuarioRepository.save(nuevoUsuario);
    }

    public AuthResponseDTO autenticar(LoginDTO loginDto) {
        // 1. Buscamos al usuario por su email único
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificamos si la contraseña coincide con el hash almacenado
        boolean coincide = passwordEncoder.matches(loginDto.getContrasena(), usuario.getContrasena());

        if (coincide) {
            return new AuthResponseDTO(true, "Login exitoso", usuario.getIdUsuario(), usuario.getNombre());
        } else {
            return new AuthResponseDTO(false, "Credenciales incorrectas", null, null);
        }
    }
}