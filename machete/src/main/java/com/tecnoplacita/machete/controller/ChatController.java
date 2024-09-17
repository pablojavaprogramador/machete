package com.tecnoplacita.machete.controller;

import com.tecnoplacita.machete.model.ChatMessage;
import com.tecnoplacita.machete.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<ChatMessage> getMessageById(@PathVariable Long id) {
        return chatService.getMessageById(id);
    }

    @PostMapping("/send")
    public ChatMessage sendMessage(@RequestBody ChatMessage message) {
        return chatService.sendMessage(message);
    }

    @PutMapping("/{id}")
    public ChatMessage updateMessage(@PathVariable Long id, @RequestBody ChatMessage message) {
        return chatService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        chatService.deleteMessage(id);
    }
}
