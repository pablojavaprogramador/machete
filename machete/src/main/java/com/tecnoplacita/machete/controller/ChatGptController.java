package com.tecnoplacita.machete.controller;

import com.tecnoplacita.machete.dto.MessageRequestGpt;
import com.tecnoplacita.machete.model.ChatMessageGpt;
import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.service.ChatServiceGpt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGptController {

	@Autowired
	private ChatServiceGpt chatService;

	// Ruta protegida por JWT
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/ask")
	public ResponseEntity<ApiResponse> askChatGpt(@RequestBody MessageRequestGpt messageRequest) {
		// Llama al servicio para obtener la respuesta de ChatGPT
		String responseMessage = chatService.getChatGptResponse(messageRequest);

		// Crea la respuesta API
		ApiResponse apiResponse = new ApiResponse(true, responseMessage);

		// Retorna la respuesta
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/ask")
	public List<ChatMessageGpt> getMethodName() {
		return chatService.obtenertodo();
	}

}
