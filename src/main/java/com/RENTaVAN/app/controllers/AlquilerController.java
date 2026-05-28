package com.RENTaVAN.app.controllers;

import com.RENTaVAN.app.dto.AlquilerRequestDTO;
import com.RENTaVAN.app.dto.AlquilerResponseDTO;
import com.RENTaVAN.app.services.AlquilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {

    private final AlquilerService alquilerService;

    @GetMapping("/cliente/{idCliente}")
    public List<AlquilerResponseDTO> listarPorCliente(@PathVariable Long idCliente) {
        return alquilerService.listarPorCliente(idCliente);
    }

    @PostMapping
    public ResponseEntity<AlquilerResponseDTO> crear(@RequestBody AlquilerRequestDTO dto) {
        try {
            return ResponseEntity.ok(alquilerService.aResponseDTO(alquilerService.crearReserva(dto)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idAlquiler}/cancelar")
    public ResponseEntity<AlquilerResponseDTO> cancelar(@PathVariable Long idAlquiler) {
        try {
            return ResponseEntity.ok(alquilerService.aResponseDTO(alquilerService.cancelarAlquiler(idAlquiler)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idAlquiler}")
    public ResponseEntity<AlquilerResponseDTO> modificar(
            @PathVariable Long idAlquiler,
            @RequestBody AlquilerRequestDTO dto) {
        try {
            return ResponseEntity.ok(alquilerService.aResponseDTO(alquilerService.modificarAlquiler(idAlquiler, dto)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
