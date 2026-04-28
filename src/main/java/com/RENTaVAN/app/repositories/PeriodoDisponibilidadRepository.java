package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.PeriodoDisponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeriodoDisponibilidadRepository extends JpaRepository<PeriodoDisponibilidad, Long> {
    // Muestra los periodos libres de una caravana concreta
    List<PeriodoDisponibilidad> findByCaravanaIdCaravana(Long idCaravana);
}
