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

    public InstrumentoDTO(Long id, String nombre, String tipo, BigDecimal precio, String descripcion, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Long getMarcaId() { return marcaId; }
    public void setMarcaId(Long marcaId) { this.marcaId = marcaId; }

    public String getMarcaNombre() { return marcaNombre; }
    public void setMarcaNombre(String marcaNombre) { this.marcaNombre = marcaNombre; }

    public String getMarcaPaisOrigen() { return marcaPaisOrigen; }
    public void setMarcaPaisOrigen(String marcaPaisOrigen) { this.marcaPaisOrigen = marcaPaisOrigen; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public String getCategoriaDescripcion() { return categoriaDescripcion; }
    public void setCategoriaDescripcion(String categoriaDescripcion) { this.categoriaDescripcion = categoriaDescripcion; }

    public List<ResenaDTO> getResenas() { return resenas; }
    public void setResenas(List<ResenaDTO> resenas) { this.resenas = resenas; }

    public Double getPromedioCalificacion() { return promedioCalificacion; }
    public void setPromedioCalificacion(Double promedioCalificacion) { this.promedioCalificacion = promedioCalificacion; }

}