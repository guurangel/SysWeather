package com.java.sysweather.controller;

import com.java.sysweather.model.Municipio;
import com.java.sysweather.repository.MunicipioRepository;
import com.java.sysweather.service.MunicipioService;
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
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public Page<Municipio> index(@PageableDefault(size = 10) Pageable pageable) {
        return municipioRepository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio create(@RequestBody @Valid Municipio municipio) {
        return municipioService.save(municipio);
    }

    @GetMapping("{id}")
    public ResponseEntity<Municipio> get(@PathVariable Long id) {
        return ResponseEntity.ok(getMunicipio(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Municipio> update(@PathVariable Long id, @RequestBody @Valid Municipio municipio) {
        Municipio updated = municipioService.update(id, municipio);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Municipio municipio = getMunicipio(id);
        municipioRepository.delete(municipio);
        return ResponseEntity.noContent().build();
    }

    private Municipio getMunicipio(Long id) {
        return municipioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado"));
    }
}
