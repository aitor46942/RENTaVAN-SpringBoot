package com.RENTaVAN.app.services;


import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
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
}