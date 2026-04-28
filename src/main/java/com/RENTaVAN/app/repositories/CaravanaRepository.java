package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Caravana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {
    // Busca todas las caravanas de un propietario específico
    List<Caravana> findByPropietarioIdUsuario(Long idPropietario);
}