package com.java.sysweather.repository;

import com.java.sysweather.model.Municipio;
import com.java.sysweather.model.enums.Estado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByNomeAndEstado(String nome, Estado estado);
}