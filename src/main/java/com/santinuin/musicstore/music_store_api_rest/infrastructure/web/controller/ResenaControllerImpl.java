package com.santinuin.musicstore.music_store_api_rest.infrastructure.web.controller;

import com.santinuin.musicstore.music_store_api_rest.application.dto.ResenaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.ResenaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
@CrossOrigin(origins = "*")
public class ResenaControllerImpl {
    private final ResenaService resenaService;

    public ResenaControllerImpl(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> getAllResenas(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) Long instrumentoId) {
        List<ResenaDTO> resenas;
        if (usuario != null) {
            resenas = resenaService.findByUsuario(usuario);
        } else if (instrumentoId != null) {
            resenas = resenaService.findByInstrumento(instrumentoId);
        } else {
            resenas = resenaService.findAll();
        }
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> getResenaById(@PathVariable Long id) {
        return resenaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instrumento/{instrumentoId}")
    public ResponseEntity<List<ResenaDTO>> getByInstrumento(@PathVariable Long instrumentoId) {
        return ResponseEntity.ok(resenaService.findByInstrumento(instrumentoId));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<ResenaDTO>> getByUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(resenaService.findByUsuario(usuario));
    }

    @GetMapping("/promedio/{instrumentoId}")
    public ResponseEntity<Double> getPromedioCalificacion(@PathVariable Long instrumentoId) {
        return ResponseEntity.ok(resenaService.getPromedioCalificacion(instrumentoId));
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> createResena(@Valid @RequestBody ResenaDTO resenaDTO) {
        ResenaDTO created = resenaService.create(resenaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenaDTO> updateResena(@PathVariable Long id, @Valid @RequestBody ResenaDTO resenaDTO) {
        return resenaService.update(id, resenaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        if (resenaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
