
package com.tecnoplacita.machete.service.impl;

import com.tecnoplacita.machete.model.ChatMessage;
import com.tecnoplacita.machete.repository.ChatRepository;
import com.tecnoplacita.machete.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }

    public Optional<ChatMessage> getMessageById(Long id) {
        return chatRepository.findById(id);
    }

    public ChatMessage sendMessage(ChatMessage message) {
        return chatRepository.save(message);
    }

    public ChatMessage updateMessage(Long id, ChatMessage message) {
        if (chatRepository.existsById(id)) {
            message.setId(id);
            return chatRepository.save(message);
        }
        return null;
    }

    public void deleteMessage(Long id) {
        chatRepository.deleteById(id);
    }
}
