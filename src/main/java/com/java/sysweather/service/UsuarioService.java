package com.java.sysweather.service;

import com.java.sysweather.model.Usuario;
import com.java.sysweather.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        // Verificar se o CPF já existe
        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("Já existe um usuário com esse CPF.");
        }

        // Verificar se o e-mail já existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com esse e-mail.");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario novoUsuario) {
        // Buscar usuário existente
        Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado."));

        // Verificar se está tentando alterar o CPF
        if (!existente.getCpf().equals(novoUsuario.getCpf())) {
            throw new IllegalArgumentException("O CPF não pode ser alterado.");
        }

        // Verificar se o novo e-mail já existe para outro usuário
        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(novoUsuario.getEmail());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Já existe outro usuário com esse e-mail.");
        }

        // Atualizar os campos permitidos
        existente.setNome(novoUsuario.getNome());
        existente.setEmail(novoUsuario.getEmail());
        existente.setSenha(novoUsuario.getSenha());
        existente.setMunicipio(novoUsuario.getMunicipio());

        return usuarioRepository.save(existente);
    }
}