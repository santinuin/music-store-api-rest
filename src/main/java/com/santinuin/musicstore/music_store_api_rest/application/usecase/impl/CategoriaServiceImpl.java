package com.santinuin.musicstore.music_store_api_rest.application.usecase.impl;

import com.santinuin.musicstore.music_store_api_rest.application.dto.CategoriaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.mapper.CategoriaMapper;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.CategoriaService;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Categoria;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findByNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(savedCategoria);
    }

    public Optional<CategoriaDTO> update(Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map(existingCategoria -> {
                    existingCategoria.setNombre(categoriaDTO.getNombre());
                    existingCategoria.setDescripcion(categoriaDTO.getDescripcion());
                    return categoriaMapper.toDTO(categoriaRepository.save(existingCategoria));
                });
    }

    public boolean delete(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}