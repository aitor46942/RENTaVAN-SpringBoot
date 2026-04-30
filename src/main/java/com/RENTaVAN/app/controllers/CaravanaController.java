package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.CaravanaDTO;
import com.RENTaVAN.app.entities.Caravana;
import com.RENTaVAN.app.services.CaravanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/caravanas")
@RequiredArgsConstructor
public class CaravanaController {

    private final CaravanaService caravanaService;

    @GetMapping
    public List<Caravana> listar() {
        //Devuelve lista completa
        return caravanaService.obtenerTodas();
    }

    @PostMapping
    public Caravana registrar(@RequestBody CaravanaDTO dto) {
        return caravanaService.guardarDesdeDTO(dto);
    }

    @GetMapping("/buscar")
    public List<Caravana> buscar(@RequestParam double lat, @RequestParam double lng, @RequestParam double radio) {
        return caravanaService.buscarCaravanasCercanas(lat, lng, radio);
    }
}