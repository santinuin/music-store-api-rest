package com.santinuin.musicstore.music_store_api_rest.application.mapper;

import com.santinuin.musicstore.music_store_api_rest.application.dto.MarcaDTO;
import com.santinuin.musicstore.music_store_api_rest.domain.model.Marca;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {

    public MarcaDTO toDTO(Marca marca) {
        if (marca == null) {
            return null;
        }

        return new MarcaDTO(
                marca.getId(),
                marca.getNombre(),
                marca.getPaisOrigen()
        );
    }

    public Marca toEntity(MarcaDTO dto) {
        if (dto == null) {
            return null;
        }

        Marca marca = new Marca(dto.getNombre(), dto.getPaisOrigen());
        marca.setId(dto.getId());
        return marca;
    }
}
