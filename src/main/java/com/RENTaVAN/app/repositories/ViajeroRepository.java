package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Viajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ViajeroRepository extends JpaRepository<Viajero, Long> {
    List<Viajero> findByAlquilerIdAlquiler(Long idAlquiler);
}
