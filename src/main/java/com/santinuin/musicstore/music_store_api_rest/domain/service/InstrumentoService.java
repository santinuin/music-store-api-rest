package com.santinuin.musicstore.music_store_api_rest.domain.service;

import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoCreateDTO;
import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InstrumentoService {
    List<InstrumentoDTO> findAll();
    Optional<InstrumentoDTO> findById(Long id);
    List<InstrumentoDTO> findByMarca(Long marcaId);
    List<InstrumentoDTO> findByCategoria(Long categoriaId);
    List<InstrumentoDTO> findByTipo(String tipo);
    List<InstrumentoDTO> findByPrecioRange(BigDecimal min, BigDecimal max);
    List<InstrumentoDTO> findWithFilters(String tipo, Long marcaId, Long categoriaId, BigDecimal precioMin, BigDecimal precioMax);
    InstrumentoDTO create(InstrumentoCreateDTO instrumentoCreateDTO);
    Optional<InstrumentoDTO> update(Long id, InstrumentoCreateDTO instrumentoCreateDTO);
    boolean delete(Long id);
}

