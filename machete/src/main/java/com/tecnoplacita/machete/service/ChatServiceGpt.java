package com.tecnoplacita.machete.service;

import java.util.List;

import com.tecnoplacita.machete.dto.MessageRequestGpt;
import com.tecnoplacita.machete.model.ChatMessageGpt;

public interface ChatServiceGpt {


	String getChatGptResponse(MessageRequestGpt messageRequest);

	List<ChatMessageGpt> obtenertodo();

}
