package com.RENTaVAN.app.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlquilerRequestDTO {
    private Long idCaravana;
    private Long idCliente;
    private Long idPeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
