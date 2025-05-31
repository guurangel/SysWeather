package com.java.sysweather.controller;

import com.java.sysweather.model.Ocorrencia;
import com.java.sysweather.repository.OcorrenciaRepository;
import com.java.sysweather.service.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping
    public Page<Ocorrencia> index(@PageableDefault(size = 10) Pageable pageable) {
        return ocorrenciaRepository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ocorrencia create(@RequestBody @Valid Ocorrencia ocorrencia) {
        return ocorrenciaService.saveOcorrencia(ocorrencia);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ocorrencia> get(@PathVariable Long id) {
        return ResponseEntity.ok(getOcorrencia(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Ocorrencia> update(@PathVariable Long id, @RequestBody @Valid Ocorrencia ocorrencia) {
        Ocorrencia updated = ocorrenciaService.updateOcorrencia(id, ocorrencia);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Ocorrencia ocorrencia = getOcorrencia(id);
        ocorrenciaRepository.delete(ocorrencia);
        return ResponseEntity.noContent().build();
    }

    private Ocorrencia getOcorrencia(Long id) {
        return ocorrenciaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorrência não encontrada"));
    }
}