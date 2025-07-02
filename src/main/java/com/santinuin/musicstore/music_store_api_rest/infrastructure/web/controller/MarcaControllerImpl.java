package com.santinuin.musicstore.music_store_api_rest.infrastructure.web.controller;

import com.santinuin.musicstore.music_store_api_rest.application.dto.MarcaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marcas")
@CrossOrigin(origins = "*")
public class MarcaControllerImpl {

    private final MarcaService marcaService;

    public MarcaControllerImpl(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> getAllMarcas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String pais) {

        List<MarcaDTO> marcas;

        if (nombre != null) {
            marcas = marcaService.findByNombre(nombre);
        } else if (pais != null) {
            marcas = marcaService.findByPais(pais);
        } else {
            marcas = marcaService.findAll();
        }

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable Long id) {
        return marcaService.findById(id)
                .map(marca -> ResponseEntity.ok(marca))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> createMarca(@Valid @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO createdMarca = marcaService.create(marcaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> updateMarca(@PathVariable Long id,
                                                @Valid @RequestBody MarcaDTO marcaDTO) {
        return marcaService.update(id, marcaDTO)
                .map(marca -> ResponseEntity.ok(marca))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id) {
        if (marcaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
