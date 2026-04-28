package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Viajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ViajeroRepository extends JpaRepository<Viajero, Long> {
    // Lista los viajeros asociados a un contrato de alquiler
    List<Viajero> findByAlquilerIdAlquiler(Long idAlquiler);
}
