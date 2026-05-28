package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByClienteIdUsuario(Long idUsuario);
}