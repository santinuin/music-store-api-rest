package com.santinuin.musicstore.music_store_api_rest.application.dto;

import jakarta.validation.constraints.NotBlank;

public class MarcaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    private String nombre;

    private String paisOrigen;

    // Constructores
    public MarcaDTO() {}

    public MarcaDTO(Long id, String nombre, String paisOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }
}
