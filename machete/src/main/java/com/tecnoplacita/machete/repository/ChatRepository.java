package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
}
