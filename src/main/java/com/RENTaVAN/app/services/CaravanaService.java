package com.RENTaVAN.app.services;

import com.RENTaVAN.app.dto.CaravanaDTO;
import com.RENTaVAN.app.dto.CaravanaResponseDTO;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.CaravanaRepository;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaravanaService {

    private final CaravanaRepository caravanaRepository;
    private final UsuarioRepository  usuarioRepository;

    public List<Caravana> obtenerTodas() {
        return caravanaRepository.findAll();
    }

    public List<Caravana> listarPorPropietario(Long idPropietario) {
        return caravanaRepository.findByPropietarioIdUsuario(idPropietario);
    }

    public Caravana guardarDesdeDTO(CaravanaDTO dto) {
        Caravana c = new Caravana();
        c.setModelo(dto.getModelo());
        c.setDescripcion(dto.getDescripcion());
        Usuario dueño = usuarioRepository.findById(dto.getIdPropietario())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        c.setPropietario(dueño);
        return caravanaRepository.save(c);
    }

    public void eliminarCaravana(Long idCaravana) {
        if (!caravanaRepository.existsById(idCaravana))
            throw new RuntimeException("Caravana no encontrada");
        caravanaRepository.deleteById(idCaravana);
    }

    public CaravanaResponseDTO aResponseDTO(Caravana entidad) {
        CaravanaResponseDTO dto = new CaravanaResponseDTO();
        dto.setIdCaravana(entidad.getIdCaravana());
        dto.setModelo(entidad.getModelo());
        dto.setDescripcion(entidad.getDescripcion());
        CaravanaResponseDTO.PropietarioResumenDTO propDto = new CaravanaResponseDTO.PropietarioResumenDTO();
        propDto.setIdUsuario(entidad.getPropietario().getIdUsuario());
        propDto.setNombre(entidad.getPropietario().getNombre());
        propDto.setTelefono(entidad.getPropietario().getTelefono());
        dto.setPropietario(propDto);
        return dto;
    }
}