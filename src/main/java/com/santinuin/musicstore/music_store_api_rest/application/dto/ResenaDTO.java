package com.santinuin.musicstore.music_store_api_rest.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ResenaDTO {
    private Long id;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;

    private String comentario;
    private LocalDateTime fechaCreacion;

    // Para evitar loops infinitos
    private Long instrumentoId;
    private String instrumentoNombre;

    // Constructores
    public ResenaDTO() {
    }

    public ResenaDTO(Long id, String usuario, Integer calificacion, String comentario,
                     LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuario = usuario;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getInstrumentoId() {
        return instrumentoId;
    }

    public void setInstrumentoId(Long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    public String getInstrumentoNombre() {
        return instrumentoNombre;
    }

    public void setInstrumentoNombre(String instrumentoNombre) {
        this.instrumentoNombre = instrumentoNombre;
    }
}