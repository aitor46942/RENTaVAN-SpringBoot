package com.RENTaVAN.app.services;

import com.RENTaVAN.app.dto.CaravanaDTO;
import com.RENTaVAN.app.dto.CaravanaResponseDTO;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.entities.Usuario;
import com.RENTaVAN.app.repositories.CaravanaRepository;
import com.RENTaVAN.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaravanaService {

    private final CaravanaRepository caravanaRepository;

    public Caravana guardarCaravana(Caravana caravana) {
        return caravanaRepository.save(caravana);
    }

    public List<Caravana> listarPorPropietario(Long idPropietario) {
        return caravanaRepository.findByPropietarioIdUsuario(idPropietario);
    }

    public List<Caravana> obtenerTodas() {
        return caravanaRepository.findAll();
    }

    public List<Caravana> buscarCaravanasCercanas(double lat, double lng, double radioKm) {
        double distanciaMetros = radioKm * 1000; // Convertimos Km a metros
        return caravanaRepository.buscarCercanas(lat, lng, distanciaMetros);
    }

    private final UsuarioRepository usuarioRepository;
    // El 4326 es el estándar de GPS (WGS84)
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public Caravana guardarDesdeDTO(CaravanaDTO dto) {
        Caravana c = new Caravana();
        c.setModelo(dto.getModelo());
        c.setDescripcion(dto.getDescripcion());

        // Buscamos al dueño en la DB de Docker
        Usuario dueño = usuarioRepository.findById(dto.getIdPropietario())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        c.setPropietario(dueño);

        // Creamos el punto GPS real para PostGIS
        Point punto = geometryFactory.createPoint(new Coordinate(dto.getLongitud(), dto.getLatitud()));
        c.setLocation(punto);

        return caravanaRepository.save(c);
    }

    public CaravanaResponseDTO aResponseDTO(Caravana entidad) {
        CaravanaResponseDTO dto = new CaravanaResponseDTO();
        dto.setIdCaravana(entidad.getIdCaravana());
        dto.setModelo(entidad.getModelo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setLocation(entidad.getLocation());

        // Creamos el resumen del propietario sin datos sensibles
        CaravanaResponseDTO.PropietarioResumenDTO propDto = new CaravanaResponseDTO.PropietarioResumenDTO();
        propDto.setIdUsuario(entidad.getPropietario().getIdUsuario());
        propDto.setNombre(entidad.getPropietario().getNombre());
        propDto.setTelefono(entidad.getPropietario().getTelefono());
        dto.setPropietario(propDto);
        return dto;
    }
}