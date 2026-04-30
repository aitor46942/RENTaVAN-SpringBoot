package com.RENTaVAN.app.repositories;

import com.RENTaVAN.app.entities.Caravana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {
    // Busca todas las caravanas de un propietario específico
    List<Caravana> findByPropietarioIdUsuario(Long idPropietario);

    @Query(value = "SELECT * FROM caravanas c WHERE ST_DWithin(c.location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :distanciaMetros)", nativeQuery = true)
    List<Caravana> buscarCercanas(@Param("lat") double lat, @Param("lng") double lng, @Param("distanciaMetros") double distanciaMetros);
}