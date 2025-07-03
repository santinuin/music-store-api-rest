package com.santinuin.musicstore.music_store_api_rest.application.usecase;

import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoCreateDTO;
import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoDTO;
import com.santinuin.musicstore.music_store_api_rest.application.mapper.InstrumentoMapper;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Categoria;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Instrumento;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Marca;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.CategoriaRepository;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.InstrumentoRepository;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.MarcaRepository;
import com.santinuin.musicstore.music_store_api_rest.domain.repository.ResenaRepository;
import com.santinuin.musicstore.music_store_api_rest.domain.service.InstrumentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstrumentoServiceImpl implements InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ResenaRepository resenaRepository;
    private final InstrumentoMapper instrumentoMapper;

    public InstrumentoServiceImpl(InstrumentoRepository instrumentoRepository,
                                  MarcaRepository marcaRepository,
                                  CategoriaRepository categoriaRepository,
                                  ResenaRepository resenaRepository,
                                  InstrumentoMapper instrumentoMapper) {
        this.instrumentoRepository = instrumentoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
        this.resenaRepository = resenaRepository;
        this.instrumentoMapper = instrumentoMapper;
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findAll() {
        List<Instrumento> instrumentos = instrumentoRepository.findAllWithMarcaAndCategoria();
        return instrumentos.stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<InstrumentoDTO> findById(Long id) {
        Instrumento instrumento = instrumentoRepository.findByIdWithMarcaAndCategoria(id);
        if (instrumento != null) {
            return Optional.of(mapToInstrumentoDTO(instrumento));
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findByMarca(Long marcaId) {
        return instrumentoRepository.findByMarcaIdWithMarca(marcaId)
                .stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findByCategoria(Long categoriaId) {
        return instrumentoRepository.findByCategoriaIdWithCategoria(categoriaId)
                .stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findByTipo(String tipo) {
        return instrumentoRepository.findByTipoIgnoreCase(tipo)
                .stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findByPrecioRange(BigDecimal min, BigDecimal max) {
        return instrumentoRepository.findByPrecioBetween(min, max)
                .stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentoDTO> findWithFilters(String tipo, Long marcaId, Long categoriaId,
                                                BigDecimal precioMin, BigDecimal precioMax) {
        return instrumentoRepository.findWithFilters(tipo, marcaId, categoriaId, precioMin, precioMax)
                .stream()
                .map(this::mapToInstrumentoDTO)
                .collect(Collectors.toList());
    }

    public InstrumentoDTO create(InstrumentoCreateDTO createDTO) {
        Marca marca = marcaRepository.findById(createDTO.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + createDTO.getMarcaId()));

        Categoria categoria = categoriaRepository.findById(createDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + createDTO.getCategoriaId()));

        Instrumento instrumento = new Instrumento(
                createDTO.getNombre(),
                createDTO.getTipo(),
                createDTO.getPrecio(),
                createDTO.getDescripcion(),
                createDTO.getStock()
        );
        instrumento.setMarca(marca);
        instrumento.setCategoria(categoria);

        Instrumento savedInstrumento = instrumentoRepository.save(instrumento);
        return mapToInstrumentoDTO(savedInstrumento);
    }

    public Optional<InstrumentoDTO> update(Long id, InstrumentoCreateDTO updateDTO) {
        return instrumentoRepository.findById(id)
                .map(existingInstrumento -> {
                    existingInstrumento.setNombre(updateDTO.getNombre());
                    existingInstrumento.setTipo(updateDTO.getTipo());
                    existingInstrumento.setPrecio(updateDTO.getPrecio());
                    existingInstrumento.setDescripcion(updateDTO.getDescripcion());
                    existingInstrumento.setStock(updateDTO.getStock());

                    if (updateDTO.getMarcaId() != null) {
                        Marca marca = marcaRepository.findById(updateDTO.getMarcaId())
                                .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + updateDTO.getMarcaId()));
                        existingInstrumento.setMarca(marca);
                    }

                    if (updateDTO.getCategoriaId() != null) {
                        Categoria categoria = categoriaRepository.findById(updateDTO.getCategoriaId())
                                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + updateDTO.getCategoriaId()));
                        existingInstrumento.setCategoria(categoria);
                    }

                    Instrumento savedInstrumento = instrumentoRepository.save(existingInstrumento);
                    return mapToInstrumentoDTO(savedInstrumento);
                });
    }

    public boolean delete(Long id) {
        if (instrumentoRepository.existsById(id)) {
            instrumentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method para mapear con cálculo de promedio
    private InstrumentoDTO mapToInstrumentoDTO(Instrumento instrumento) {
        InstrumentoDTO dto = instrumentoMapper.toDTO(instrumento);

        // Calcular promedio de calificaciones
        Double promedio = resenaRepository.findAverageCalificacionByInstrumentoId(instrumento.getId());
        dto.setPromedioCalificacion(promedio);

        return dto;
    }
}