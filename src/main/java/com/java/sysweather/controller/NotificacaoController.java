package com.java.sysweather.controller;

import com.java.sysweather.model.NotificacaoOcorrencia;
import com.java.sysweather.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<NotificacaoOcorrencia> listarPorUsuario(@PathVariable Long usuarioId) {
        // Apenas lista as notificações do usuário, sem filtro por "lido"
        return notificacaoRepository.findByUsuarioIdOrderByDataEnvioDesc(usuarioId);
    }

}