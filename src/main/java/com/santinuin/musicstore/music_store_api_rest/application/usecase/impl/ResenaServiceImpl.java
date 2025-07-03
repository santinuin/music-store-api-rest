package com.santinuin.musicstore.music_store_api_rest.application.usecase.impl;

import com.santinuin.musicstore.music_store_api_rest.application.dto.ResenaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.mapper.ResenaMapper;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.ResenaService;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Instrumento;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Resena;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.InstrumentoRepository;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.ResenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final InstrumentoRepository instrumentoRepository;
    private final ResenaMapper resenaMapper;

    public ResenaServiceImpl(ResenaRepository resenaRepository,
                             InstrumentoRepository instrumentoRepository,
                             ResenaMapper resenaMapper) {
        this.resenaRepository = resenaRepository;
        this.instrumentoRepository = instrumentoRepository;
        this.resenaMapper = resenaMapper;
    }

    @Transactional(readOnly = true)
    public List<ResenaDTO> findAll() {
        return resenaRepository.findAll()
                .stream()
                .map(resenaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ResenaDTO> findById(Long id) {
        return resenaRepository.findById(id)
                .map(resenaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<ResenaDTO> findByInstrumento(Long instrumentoId) {
        return resenaRepository.findByInstrumentoIdWithInstrumento(instrumentoId)
                .stream()
                .map(resenaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResenaDTO> findByUsuario(String usuario) {
        return resenaRepository.findByUsuarioIgnoreCase(usuario)
                .stream()
                .map(resenaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Double getPromedioCalificacion(Long instrumentoId) {
        return resenaRepository.findAverageCalificacionByInstrumentoId(instrumentoId);
    }

    public ResenaDTO create(ResenaDTO resenaDTO) {
        Instrumento instrumento = instrumentoRepository.findById(resenaDTO.getInstrumentoId())
                .orElseThrow(() -> new RuntimeException("Instrumento no encontrado: " + resenaDTO.getInstrumentoId()));

        Resena resena = resenaMapper.toEntity(resenaDTO);
        resena.setInstrumento(instrumento);

        Resena savedResena = resenaRepository.save(resena);
        return resenaMapper.toDTO(savedResena);
    }

    public Optional<ResenaDTO> update(Long id, ResenaDTO resenaDTO) {
        return resenaRepository.findById(id)
                .map(existingResena -> {
                    existingResena.setUsuario(resenaDTO.getUsuario());
                    existingResena.setCalificacion(resenaDTO.getCalificacion());
                    existingResena.setComentario(resenaDTO.getComentario());
                    return resenaMapper.toDTO(resenaRepository.save(existingResena));
                });
    }

    public boolean delete(Long id) {
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}