package com.santinuin.musicstore.music_store_api_rest.domain.repository;

import com.santinuin.musicstore.music_store_api_rest.domain.model.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {

    // Búsquedas básicas
    List<Instrumento> findByNombreContainingIgnoreCase(String nombre);

    List<Instrumento> findByTipoIgnoreCase(String tipo);

    List<Instrumento> findByMarcaId(Long marcaId);

    List<Instrumento> findByCategoriaId(Long categoriaId);

    // Búsquedas por precio
    List<Instrumento> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax);

    List<Instrumento> findByPrecioLessThanEqual(BigDecimal precio);

    List<Instrumento> findByPrecioGreaterThanEqual(BigDecimal precio);

    // Búsquedas por stock
    List<Instrumento> findByStockGreaterThan(Integer stock);

    List<Instrumento> findByStockEquals(Integer stock);

    // Consultas personalizadas con JOIN FETCH para evitar N+1
    @Query("SELECT i FROM Instrumento i JOIN FETCH i.marca JOIN FETCH i.categoria")
    List<Instrumento> findAllWithMarcaAndCategoria();

    @Query("SELECT i FROM Instrumento i JOIN FETCH i.marca JOIN FETCH i.categoria WHERE i.id = :id")
    Instrumento findByIdWithMarcaAndCategoria(@Param("id") Long id);

    @Query("SELECT i FROM Instrumento i JOIN FETCH i.marca m WHERE m.id = :marcaId")
    List<Instrumento> findByMarcaIdWithMarca(@Param("marcaId") Long marcaId);

    @Query("SELECT i FROM Instrumento i JOIN FETCH i.categoria c WHERE c.id = :categoriaId")
    List<Instrumento> findByCategoriaIdWithCategoria(@Param("categoriaId") Long categoriaId);

    // Búsqueda compleja
    @Query("SELECT i FROM Instrumento i JOIN FETCH i.marca JOIN FETCH i.categoria " +
            "WHERE (:tipo IS NULL OR LOWER(i.tipo) = LOWER(:tipo)) " +
            "AND (:marcaId IS NULL OR i.marca.id = :marcaId) " +
            "AND (:categoriaId IS NULL OR i.categoria.id = :categoriaId) " +
            "AND (:precioMin IS NULL OR i.precio >= :precioMin) " +
            "AND (:precioMax IS NULL OR i.precio <= :precioMax) " +
            "ORDER BY i.nombre")
    List<Instrumento> findWithFilters(@Param("tipo") String tipo,
                                      @Param("marcaId") Long marcaId,
                                      @Param("categoriaId") Long categoriaId,
                                      @Param("precioMin") BigDecimal precioMin,
                                      @Param("precioMax") BigDecimal precioMax);
}
