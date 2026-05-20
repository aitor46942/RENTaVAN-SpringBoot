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
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // CORRECCIÓN: eliminado registrarUsuario() que guardaba sin BCrypt
    // Este es el único método de registro, siempre cifra la contraseña
    public AuthResponseDTO registrar(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return new AuthResponseDTO(false, "El email ya está registrado", null, null);
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(dto.getNombre());
        nuevo.setEmail(dto.getEmail());
        nuevo.setTelefono(dto.getTelefono());

        // BCrypt genera un hash seguro, nunca se guarda la contraseña original
        nuevo.setContrasena(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(nuevo);
        return new AuthResponseDTO(true, "Registro exitoso", guardado.getIdUsuario(), guardado.getNombre());
    }

    public AuthResponseDTO autenticar(LoginDTO loginDto) {
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail()).orElse(null);

        // CORRECCIÓN: antes lanzaba RuntimeException que devolvía 500 al móvil
        // Ahora devuelve una respuesta controlada con el mensaje adecuado
        if (usuario == null) {
            return new AuthResponseDTO(false, "Usuario no encontrado", null, null);
        }

        // BCrypt compara la contraseña recibida con el hash de la BD
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
}




//@Service
//@RequiredArgsConstructor // Crea el constructor para la inyección automática
//public class UsuarioService {
//
//    private final UsuarioRepository usuarioRepository;
//
//    public Usuario registrarUsuario(Usuario usuario) {
//        // Aquí podrías añadir lógica para encriptar la contraseña
//        return usuarioRepository.save(usuario);
//    }
//
//    public List<Usuario> obtenerTodos() {
//        return usuarioRepository.findAll();
//    }
//
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public Usuario registrar(UsuarioRegistroDTO dto) {
//        // 1. Verificar si el email ya existe para evitar duplicados
//        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
//            throw new RuntimeException("El email ya está registrado");
//        }
//
//        Usuario nuevoUsuario = new Usuario();
//        nuevoUsuario.setNombre(dto.getNombre());
//        nuevoUsuario.setEmail(dto.getEmail());
//        nuevoUsuario.setTelefono(dto.getTelefono());
//
//        // 2. CIFRADO CRÍTICO
//        String hash = passwordEncoder.encode(dto.getPassword());
//        nuevoUsuario.setContrasena(hash);
//
//        return usuarioRepository.save(nuevoUsuario);
//    }
//
//    public AuthResponseDTO autenticar(LoginDTO loginDto) {
//        // 1. Buscamos al usuario por su email único
//        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail())
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//        // 2. Verificamos si la contraseña coincide con el hash almacenado
//        boolean coincide = passwordEncoder.matches(loginDto.getContrasena(), usuario.getContrasena());
//
//        if (coincide) {
//            return new AuthResponseDTO(true, "Login exitoso", usuario.getIdUsuario(), usuario.getNombre());
//        } else {
//            return new AuthResponseDTO(false, "Credenciales incorrectas", null, null);
//        }
//    }
//}