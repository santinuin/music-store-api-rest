package com.santinuin.musicstore.music_store_api_rest.application.usecase;

import com.santinuin.musicstore.music_store_api_rest.application.dto.ResenaDTO;

import java.util.List;
import java.util.Optional;

public interface ResenaService {
    List<ResenaDTO> findAll();
    Optional<ResenaDTO> findById(Long id);
    List<ResenaDTO> findByInstrumento(Long instrumentoId);
    List<ResenaDTO> findByUsuario(String usuario);
    Double getPromedioCalificacion(Long instrumentoId);
    ResenaDTO create(ResenaDTO resenaDTO);
    Optional<ResenaDTO> update(Long id, ResenaDTO resenaDTO);
    boolean delete(Long id);
}

