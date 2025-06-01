package com.java.sysweather.mapper;

import com.java.sysweather.dto.response.UsuarioResponse;
import com.java.sysweather.dto.response.UsuarioResumoResponse;
import com.java.sysweather.mapper.MunicipioMapper;
import com.java.sysweather.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
            .id(usuario.getId())
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .dataCadastro(usuario.getDataCadastro())
            .municipio(MunicipioMapper.toResumo(usuario.getMunicipio()))
            .build();
    }

    public static UsuarioResumoResponse toResumo(Usuario usuario) {
        return UsuarioResumoResponse.builder()
            .id(usuario.getId())
            .nome(usuario.getNome())
            .build();
    }
}
