package com.santinuin.musicstore.music_store_api_rest.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class InstrumentoDTO {
    private Long id;

    @NotBlank(message = "El nombre del instrumento es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo del instrumento es obligatorio")
    private String tipo;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    private String descripcion;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    // Referencias a otras entidades
    private Long marcaId;
    private String marcaNombre;
    private String marcaPaisOrigen;

    private Long categoriaId;
    private String categoriaNombre;
    private String categoriaDescripcion;

    // Para casos donde necesitemos las reseñas
    private List<ResenaDTO> resenas;
    private Double promedioCalificacion;

    // Constructores
    public InstrumentoDTO() {
    }

    public InstrumentoDTO(Long id, String nombre, String tipo, BigDecimal precio,
                          String descripcion, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
    }
}