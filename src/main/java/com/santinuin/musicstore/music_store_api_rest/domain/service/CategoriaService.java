package com.santinuin.musicstore.music_store_api_rest.domain.service;

import com.santinuin.musicstore.music_store_api_rest.application.dto.CategoriaDTO;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<CategoriaDTO> findAll();
    Optional<CategoriaDTO> findById(Long id);
    List<CategoriaDTO> findByNombre(String nombre);
    CategoriaDTO create(CategoriaDTO categoriaDTO);
    Optional<CategoriaDTO> update(Long id, CategoriaDTO categoriaDTO);
    boolean delete(Long id);
}

