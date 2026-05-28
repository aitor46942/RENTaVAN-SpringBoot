package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.PeriodoDisponibilidadDTO;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.entities.PeriodoDisponibilidad;
import com.RENTaVAN.app.repositories.CaravanaRepository;
import com.RENTaVAN.app.repositories.PeriodoDisponibilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/periodos")
@RequiredArgsConstructor
public class PeriodoDisponibilidadController {

    private final PeriodoDisponibilidadRepository periodoRepository;
    private final CaravanaRepository caravanaRepository;

    @GetMapping("/caravana/{idCaravana}")
    public List<PeriodoDisponibilidadDTO> listarPorCaravana(@PathVariable Long idCaravana) {
        return periodoRepository.findByCaravanaIdCaravana(idCaravana)
                .stream().map(this::aDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<PeriodoDisponibilidadDTO> crear(@RequestBody PeriodoDisponibilidadDTO dto) {
        Caravana caravana = caravanaRepository.findById(dto.getIdCaravana()).orElse(null);
        if (caravana == null) return ResponseEntity.badRequest().build();
        PeriodoDisponibilidad p = new PeriodoDisponibilidad();
        p.setCaravana(caravana);
        p.setFechaInicio(dto.getFechaInicio());
        p.setFechaFin(dto.getFechaFin());
        return ResponseEntity.ok(aDTO(periodoRepository.save(p)));
    }

    @DeleteMapping("/{idPeriodo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idPeriodo) {
        if (!periodoRepository.existsById(idPeriodo)) return ResponseEntity.notFound().build();
        periodoRepository.deleteById(idPeriodo);
        return ResponseEntity.noContent().build();
    }

    private PeriodoDisponibilidadDTO aDTO(PeriodoDisponibilidad p) {
        PeriodoDisponibilidadDTO dto = new PeriodoDisponibilidadDTO();
        dto.setIdPeriodo(p.getIdPeriodo());
        dto.setIdCaravana(p.getCaravana().getIdCaravana());
        dto.setFechaInicio(p.getFechaInicio());
        dto.setFechaFin(p.getFechaFin());
        return dto;
    }
}
