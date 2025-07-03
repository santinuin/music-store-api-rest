package com.santinuin.musicstore.music_store_api_rest.application.usecase.impl;

import com.santinuin.musicstore.music_store_api_rest.application.dto.MarcaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.mapper.MarcaMapper;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.MarcaService;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Marca;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaServiceImpl(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }

    @Transactional(readOnly = true)
    public List<MarcaDTO> findAll() {
        return marcaRepository.findAll()
                .stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MarcaDTO> findById(Long id) {
        return marcaRepository.findById(id)
                .map(marcaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<MarcaDTO> findByNombre(String nombre) {
        return marcaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MarcaDTO> findByPais(String pais) {
        return marcaRepository.findByPaisOrigenIgnoreCase(pais)
                .stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MarcaDTO create(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDTO(savedMarca);
    }

    public Optional<MarcaDTO> update(Long id, MarcaDTO marcaDTO) {
        return marcaRepository.findById(id)
                .map(existingMarca -> {
                    existingMarca.setNombre(marcaDTO.getNombre());
                    existingMarca.setPaisOrigen(marcaDTO.getPaisOrigen());
                    return marcaMapper.toDTO(marcaRepository.save(existingMarca));
                });
    }

    public boolean delete(Long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
