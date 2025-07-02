package com.santinuin.musicstore.music_store_api_rest.application.mapper;

import com.santinuin.musicstore.music_store_api_rest.application.dto.ResenaDTO;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Resena;
import org.springframework.stereotype.Component;

@Component
public class ResenaMapper {

    public ResenaDTO toDTO(Resena resena) {
        if (resena == null) {
            return null;
        }

        ResenaDTO dto = new ResenaDTO(
                resena.getId(),
                resena.getUsuario(),
                resena.getCalificacion(),
                resena.getComentario(),
                resena.getFechaCreacion()
        );

        // Mapear información básica del instrumento para evitar loops
        if (resena.getInstrumento() != null) {
            dto.setInstrumentoId(resena.getInstrumento().getId());
            dto.setInstrumentoNombre(resena.getInstrumento().getNombre());
        }

        return dto;
    }

    public Resena toEntity(ResenaDTO dto) {
        if (dto == null) {
            return null;
        }

        Resena resena = new Resena(
                dto.getUsuario(),
                dto.getCalificacion(),
                dto.getComentario()
        );
        resena.setId(dto.getId());

        if (dto.getFechaCreacion() != null) {
            resena.setFechaCreacion(dto.getFechaCreacion());
        }

        return resena;
    }
}
