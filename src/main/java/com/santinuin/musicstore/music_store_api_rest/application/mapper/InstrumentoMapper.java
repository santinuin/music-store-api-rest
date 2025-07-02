package com.santinuin.musicstore.music_store_api_rest.application.mapper;

import com.santinuin.musicstore.music_store_api_rest.application.dto.InstrumentoDTO;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Instrumento;
import org.springframework.stereotype.Component;

@Component
public class InstrumentoMapper {

    public InstrumentoDTO toDTO(Instrumento instrumento) {
        if (instrumento == null) {
            return null;
        }

        InstrumentoDTO dto = new InstrumentoDTO(
                instrumento.getId(),
                instrumento.getNombre(),
                instrumento.getTipo(),
                instrumento.getPrecio(),
                instrumento.getDescripcion(),
                instrumento.getStock()
        );

        // Mapear información de marca
        if (instrumento.getMarca() != null) {
            dto.setMarcaId(instrumento.getMarca().getId());
            dto.setMarcaNombre(instrumento.getMarca().getNombre());
            dto.setMarcaPaisOrigen(instrumento.getMarca().getPaisOrigen());
        }

        // Mapear información de categoría
        if (instrumento.getCategoria() != null) {
            dto.setCategoriaId(instrumento.getCategoria().getId());
            dto.setCategoriaNombre(instrumento.getCategoria().getNombre());
            dto.setCategoriaDescripcion(instrumento.getCategoria().getDescripcion());
        }

        return dto;
    }

    public Instrumento toEntity(InstrumentoDTO dto) {
        if (dto == null) {
            return null;
        }

        Instrumento instrumento = new Instrumento(
                dto.getNombre(),
                dto.getTipo(),
                dto.getPrecio(),
                dto.getDescripcion(),
                dto.getStock()
        );
        instrumento.setId(dto.getId());

        return instrumento;
    }
}
