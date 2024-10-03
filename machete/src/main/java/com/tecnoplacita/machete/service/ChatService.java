package com.tecnoplacita.machete.service;

import java.util.List;
import java.util.Optional;

import com.tecnoplacita.machete.dto.ApiResponse;

import com.tecnoplacita.machete.model.ChatMessage;

public interface ChatService {

	List<ChatMessage> getAllMessages();

	Optional<ChatMessage> getMessageById(Long id);

	ChatMessage sendMessage(ChatMessage message);

	ChatMessage updateMessage(Long id, ChatMessage message);

	void deleteMessage(Long id);
    
}
