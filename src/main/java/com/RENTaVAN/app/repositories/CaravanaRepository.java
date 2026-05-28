package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Caravana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {
    List<Caravana> findByPropietarioIdUsuario(Long idPropietario);
}