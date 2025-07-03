package com.santinuin.musicstore.music_store_api_rest.infrastructure.web.controller;

import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoCreateDTO;
import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoDTO;
import com.santinuin.musicstore.music_store_api_rest.domain.service.InstrumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instrumentos")
@CrossOrigin(origins = "*")
public class InstrumentoControllerImpl {
    private final InstrumentoService instrumentoService;

    public InstrumentoControllerImpl(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoDTO>> getAllInstrumentos(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {
        List<InstrumentoDTO> instrumentos;
        if (tipo != null || marcaId != null || categoriaId != null || precioMin != null || precioMax != null) {
            instrumentos = instrumentoService.findWithFilters(tipo, marcaId, categoriaId, precioMin, precioMax);
        } else {
            instrumentos = instrumentoService.findAll();
        }
        return ResponseEntity.ok(instrumentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> getInstrumentoById(@PathVariable Long id) {
        return instrumentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<InstrumentoDTO>> getByMarca(@PathVariable Long marcaId) {
        return ResponseEntity.ok(instrumentoService.findByMarca(marcaId));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<InstrumentoDTO>> getByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(instrumentoService.findByCategoria(categoriaId));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<InstrumentoDTO>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(instrumentoService.findByTipo(tipo));
    }

    @GetMapping("/precio")
    public ResponseEntity<List<InstrumentoDTO>> getByPrecioRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(instrumentoService.findByPrecioRange(min, max));
    }

    @PostMapping
    public ResponseEntity<InstrumentoDTO> createInstrumento(@Valid @RequestBody InstrumentoCreateDTO createDTO) {
        InstrumentoDTO created = instrumentoService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> updateInstrumento(@PathVariable Long id, @Valid @RequestBody InstrumentoCreateDTO updateDTO) {
        return instrumentoService.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrumento(@PathVariable Long id) {
        if (instrumentoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
