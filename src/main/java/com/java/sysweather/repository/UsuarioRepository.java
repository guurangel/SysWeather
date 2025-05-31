package com.java.sysweather.repository;

import com.java.sysweather.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByMunicipioId(Long municipioId);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}