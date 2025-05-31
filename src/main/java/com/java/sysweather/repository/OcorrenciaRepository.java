package com.java.sysweather.repository;

import com.java.sysweather.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findByMunicipioId(Long municipioId);
}