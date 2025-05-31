package com.java.sysweather.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.sysweather.model.NotificacaoOcorrencia;
import com.java.sysweather.model.Ocorrencia;
import com.java.sysweather.model.Usuario;
import com.java.sysweather.repository.MunicipioRepository;
import com.java.sysweather.repository.NotificacaoRepository;
import com.java.sysweather.repository.OcorrenciaRepository;
import com.java.sysweather.repository.UsuarioRepository;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    public Ocorrencia saveOcorrencia(Ocorrencia ocorrencia) {
        var municipioId = ocorrencia.getMunicipio().getId();
        if (!municipioRepository.existsById(municipioId)) {
            throw new IllegalArgumentException("Município associado não existe.");
        }

        // Salvar a ocorrência
        Ocorrencia saved = ocorrenciaRepository.save(ocorrencia);

        // Buscar usuários daquele município
        List<Usuario> usuarios = usuarioRepository.findByMunicipioId(municipioId);

        // Criar notificações para cada usuário
        List<NotificacaoOcorrencia> notificacoes = usuarios.stream().map(usuario -> {
            NotificacaoOcorrencia n = new NotificacaoOcorrencia();
            n.setUsuario(usuario);
            n.setOcorrencia(saved);  // <-- Associa a ocorrência aqui
            n.setMensagem("Nova ocorrência: " + saved.getTipo().name() +
                " com risco " + saved.getNivelRisco().name() +
                " no município " + saved.getMunicipio().getNome());
            n.setDataEnvio(LocalDateTime.now());
            return n;
        }).toList();

        // Salvar todas notificações
        notificacaoRepository.saveAll(notificacoes);

        return saved;
    }

    public Ocorrencia updateOcorrencia(Long id, Ocorrencia novaOcorrencia) {
        // Verificar se a ocorrência existe
        Ocorrencia existente = ocorrenciaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ocorrência com ID " + id + " não encontrada."));

        // Verificar se o município informado na nova ocorrência existe
        Long municipioId = novaOcorrencia.getMunicipio().getId();
        if (!municipioRepository.existsById(municipioId)) {
            throw new IllegalArgumentException("Município com ID " + municipioId + " não existe.");
        }

        // Atualizar os dados
        existente.setDescricao(novaOcorrencia.getDescricao());
        existente.setTipo(novaOcorrencia.getTipo());
        existente.setNivelRisco(novaOcorrencia.getNivelRisco());
        existente.setDataOcorrencia(novaOcorrencia.getDataOcorrencia());
        existente.setMunicipio(novaOcorrencia.getMunicipio());

        // Salvar e retornar a ocorrência atualizada
        return ocorrenciaRepository.save(existente);
    }
}
