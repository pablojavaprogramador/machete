package com.tecnoplacita.machete.repository;


import com.tecnoplacita.machete.model.ChatMessageGpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepositoryGpt extends JpaRepository<ChatMessageGpt, Long> {

    // Método para buscar una respuesta según el mensaje
	ChatMessageGpt findByMessage(String message);
}
