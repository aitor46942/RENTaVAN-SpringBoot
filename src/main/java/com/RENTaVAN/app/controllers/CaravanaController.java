package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.CaravanaDTO;
import com.RENTaVAN.app.dto.CaravanaResponseDTO;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.services.CaravanaService;
import lombok.RequiredArgsConstructor;
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
        //Devuelve lista completa

        // CORRECCIÓN: devolvemos DTO en vez de la entidad directa
        // La entidad Caravana tiene una relación con Usuario que a su vez
        // tiene una lista de Caravanas → bucle infinito en la serialización JSON
        return caravanaService.obtenerTodas()
                .stream()
                .map(caravanaService::aResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CaravanaResponseDTO registrar(@RequestBody CaravanaDTO dto) {
        return caravanaService.aResponseDTO(caravanaService.guardarDesdeDTO(dto));
    }

    @GetMapping("/propietario/{idPropietario}")
    public List<CaravanaResponseDTO> listarPorPropietario(@PathVariable Long idPropietario) {
        return caravanaService.listarPorPropietario(idPropietario)
                .stream()
                .map(caravanaService::aResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar")
    public List<CaravanaResponseDTO> buscar(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double radio) {
        return caravanaService.buscarCaravanasCercanas(lat, lng, radio)
                .stream()
                .map(caravanaService::aResponseDTO)
                .collect(Collectors.toList());
    }
}