package com.santinuin.musicstore.music_store_api_rest.domain.repository;

import com.santinuin.musicstore.music_store_api_rest.domain.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNombreContainingIgnoreCase(String nombre);

    List<Marca> findByPaisOrigenIgnoreCase(String paisOrigen);

    @Query("SELECT m FROM Marca m WHERE m.paisOrigen = ?1 ORDER BY m.nombre")
    List<Marca> findByPaisOrigenOrderByNombre(String paisOrigen);
}
