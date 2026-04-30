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

    public Usuario registrarUsuario(UsuarioRegistroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        // Aplicamos hashing con salting automático mediante BCrypt
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(encodedPassword);

        return usuarioRepository.save(usuario);
    }

    public AuthResponseDTO autenticar(LoginDTO loginDto) {
        // 1. Buscamos al usuario por su email único
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificamos si la contraseña coincide con el hash almacenado
        boolean coincide = passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword());

        if (coincide) {
            return new AuthResponseDTO(true, "Login exitoso", usuario.getIdUsuario(), usuario.getNombre());
        } else {
            return new AuthResponseDTO(false, "Credenciales incorrectas", null, null);
        }
    }
}