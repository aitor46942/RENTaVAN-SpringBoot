package com.RENTaVAN.app.dto;

import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class CaravanaResponseDTO {
    private Long idCaravana;
    private String modelo;
    private String descripcion;
    private Point location; // Se serializará como GeoJSON gracias a JtsModule
    private PropietarioResumenDTO propietario;

    @Data
    public static class PropietarioResumenDTO {
        private Long idUsuario;
        private String nombre;
        private String telefono;
        // Excluimos password y la lista circular de caravanas por seguridad y eficiencia
    }
}