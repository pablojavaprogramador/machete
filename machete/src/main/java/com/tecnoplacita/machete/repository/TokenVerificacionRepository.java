package com.tecnoplacita.machete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnoplacita.machete.model.TokenVerificacion;

@Repository
public interface TokenVerificacionRepository extends JpaRepository<TokenVerificacion, Long> {
    TokenVerificacion findByToken(String token);
}
