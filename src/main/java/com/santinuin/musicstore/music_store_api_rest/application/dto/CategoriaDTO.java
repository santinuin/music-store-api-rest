package com.santinuin.musicstore.music_store_api_rest.application.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    private String descripcion;

    // Constructores
    public CategoriaDTO() {}

    public CategoriaDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}