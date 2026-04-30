package com.RENTaVAN.app.services;

import com.RENTaVAN.app.entities.Alquiler;
import com.RENTaVAN.app.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;

    @Transactional
    public Alquiler crearReserva(Alquiler alquiler) {
        // Lógica de negocio:
        // 1. Verificar disponibilidad de fechas
        // 2. Calcular precio total (si tuvieras campo de precio)
        alquiler.setEstado("PENDIENTE");
        return alquilerRepository.save(alquiler);
    }
}