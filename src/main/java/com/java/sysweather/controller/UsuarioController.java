package com.java.sysweather.controller;

import com.java.sysweather.dto.response.UsuarioResponse;
import com.java.sysweather.mapper.UsuarioMapper;
import com.java.sysweather.model.Usuario;
import com.java.sysweather.repository.UsuarioRepository;
import com.java.sysweather.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioResponse> index(@PageableDefault(size = 10) Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        // Mapear cada Usuario para UsuarioResponse
        return usuarios.map(UsuarioMapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse create(@RequestBody @Valid Usuario usuario) {
        Usuario saved = usuarioService.saveUsuario(usuario);
        return UsuarioMapper.toResponse(saved);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponse> get(@PathVariable Long id) {
        Usuario usuario = getUsuario(id);
        return ResponseEntity.ok(UsuarioMapper.toResponse(usuario));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        Usuario updated = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(UsuarioMapper.toResponse(updated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Usuario usuario = getUsuario(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    private Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}