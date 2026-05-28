package com.RENTaVAN.app.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PeriodoDisponibilidadDTO {
    private Long      idPeriodo;
    private Long      idCaravana;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}