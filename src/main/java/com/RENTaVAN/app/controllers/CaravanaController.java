package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.CaravanaDTO;
import com.RENTaVAN.app.dto.CaravanaResponseDTO;
import com.RENTaVAN.app.services.CaravanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/caravanas")
@RequiredArgsConstructor
public class CaravanaController {

    private final CaravanaService caravanaService;

    @GetMapping
    public List<CaravanaResponseDTO> listar() {
        return caravanaService.obtenerTodas().stream()
                .map(caravanaService::aResponseDTO).collect(Collectors.toList());
    }

    @PostMapping
    public CaravanaResponseDTO registrar(@RequestBody CaravanaDTO dto) {
        return caravanaService.aResponseDTO(caravanaService.guardarDesdeDTO(dto));
    }

    @GetMapping("/propietario/{idPropietario}")
    public List<CaravanaResponseDTO> listarPorPropietario(@PathVariable Long idPropietario) {
        return caravanaService.listarPorPropietario(idPropietario).stream()
                .map(caravanaService::aResponseDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/{idCaravana}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idCaravana) {
        try {
            caravanaService.eliminarCaravana(idCaravana);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}