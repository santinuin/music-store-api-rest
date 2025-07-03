package com.santinuin.musicstore.music_store_api_rest.infrastructure.web.controller;

import com.santinuin.musicstore.music_store_api_rest.application.dto.CategoriaDTO;
import com.santinuin.musicstore.music_store_api_rest.application.usecase.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "*")
public class CategoriaControllerImpl {

    private final CategoriaService categoriaService;

    public CategoriaControllerImpl(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias(
            @RequestParam(required = false) String nombre) {

        List<CategoriaDTO> categorias;

        if (nombre != null) {
            categorias = categoriaService.findByNombre(nombre);
        } else {
            categorias = categoriaService.findAll();
        }

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO createdCategoria = categoriaService.create(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
                                                        @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.update(id, categoriaDTO)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        if (categoriaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
