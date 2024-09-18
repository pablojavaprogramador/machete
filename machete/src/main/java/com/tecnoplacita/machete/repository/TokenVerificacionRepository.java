package com.tecnoplacita.machete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnoplacita.machete.model.TokenVerificacion;

@Repository
public interface TokenVerificacionRepository extends JpaRepository<TokenVerificacion, Long> {
    TokenVerificacion findByToken(String token);
    Optional<TokenVerificacion> findByUsuarioId(Integer integer); // Agregado
}

