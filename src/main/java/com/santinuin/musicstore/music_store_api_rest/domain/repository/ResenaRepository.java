package com.santinuin.musicstore.music_store_api_rest.domain.repository;

import com.santinuin.musicstore.music_store_api_rest.domain.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByInstrumentoId(Long instrumentoId);

    List<Resena> findByUsuarioIgnoreCase(String usuario);

    List<Resena> findByCalificacion(Integer calificacion);

    List<Resena> findByInstrumentoIdOrderByFechaCreacionDesc(Long instrumentoId);

    // Estadísticas
    @Query("SELECT AVG(r.calificacion) FROM Resena r WHERE r.instrumento.id = :instrumentoId")
    Double findAverageCalificacionByInstrumentoId(@Param("instrumentoId") Long instrumentoId);

    @Query("SELECT COUNT(r) FROM Resena r WHERE r.instrumento.id = :instrumentoId")
    Long countByInstrumentoId(@Param("instrumentoId") Long instrumentoId);

    @Query("SELECT r FROM Resena r JOIN FETCH r.instrumento WHERE r.instrumento.id = :instrumentoId ORDER BY r.fechaCreacion DESC")
    List<Resena> findByInstrumentoIdWithInstrumento(@Param("instrumentoId") Long instrumentoId);
}
