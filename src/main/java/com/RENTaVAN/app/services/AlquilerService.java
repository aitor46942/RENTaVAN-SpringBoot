package com.RENTaVAN.app.services;

import com.RENTaVAN.app.dto.AlquilerRequestDTO;
import com.RENTaVAN.app.dto.AlquilerResponseDTO;
import com.RENTaVAN.app.entities.Alquiler;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.AlquilerRepository;
import com.RENTaVAN.app.repositories.CaravanaRepository;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlquilerService {

    private final AlquilerRepository  alquilerRepository;
    private final CaravanaRepository  caravanaRepository;
    private final UsuarioRepository   usuarioRepository;

    public List<AlquilerResponseDTO> listarPorCliente(Long idCliente) {
        return alquilerRepository.findByClienteIdUsuario(idCliente)
                .stream().map(this::aResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public Alquiler crearReserva(AlquilerRequestDTO dto) {
        Caravana caravana = caravanaRepository.findById(dto.getIdCaravana())
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Usuario cliente = usuarioRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Alquiler alquiler = new Alquiler();
        alquiler.setCaravana(caravana);
        alquiler.setCliente(cliente);
        alquiler.setFechaInicio(dto.getFechaInicio());
        alquiler.setFechaFin(dto.getFechaFin());
        alquiler.setEstado("PENDIENTE");
        return alquilerRepository.save(alquiler);
    }

    @Transactional
    public Alquiler cancelarAlquiler(Long idAlquiler) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
        if (LocalDate.now().isAfter(alquiler.getFechaInicio()))
            throw new RuntimeException("No se puede cancelar un alquiler ya iniciado");
        alquiler.setEstado("CANCELADO");
        return alquilerRepository.save(alquiler);
    }

    @Transactional
    public Alquiler modificarAlquiler(Long idAlquiler, AlquilerRequestDTO dto) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
        if (LocalDate.now().isAfter(alquiler.getFechaInicio()))
            throw new RuntimeException("No se puede modificar un alquiler ya iniciado");
        alquiler.setFechaInicio(dto.getFechaInicio());
        alquiler.setFechaFin(dto.getFechaFin());
        return alquilerRepository.save(alquiler);
    }

    public AlquilerResponseDTO aResponseDTO(Alquiler a) {
        AlquilerResponseDTO dto = new AlquilerResponseDTO();
        dto.setIdAlquiler(a.getIdAlquiler());
        dto.setIdCaravana(a.getCaravana().getIdCaravana());
        dto.setModeloCaravana(a.getCaravana().getModelo());
        dto.setIdCliente(a.getCliente().getIdUsuario());
        dto.setFechaInicio(a.getFechaInicio() != null ? a.getFechaInicio().toString() : null);
        dto.setFechaFin(a.getFechaFin()   != null ? a.getFechaFin().toString()   : null);
        dto.setEstado(a.getEstado());
        return dto;
    }
}