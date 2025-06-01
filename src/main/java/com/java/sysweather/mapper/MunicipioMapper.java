package com.java.sysweather.mapper;

import com.java.sysweather.dto.response.MunicipioResumoResponse;
import com.java.sysweather.model.Municipio;

public class MunicipioMapper {

    public static MunicipioResumoResponse toResumo(Municipio municipio) {
        return MunicipioResumoResponse.builder()
            .id(municipio.getId())
            .nome(municipio.getNome())
            .estado(municipio.getEstado().name())
            .build();
    }
}