package com.tecnoplacita.machete.service.impl;

import com.tecnoplacita.machete.repository.ChatRepositoryGpt;

import com.tecnoplacita.machete.dto.MessageRequestGpt;
import com.tecnoplacita.machete.model.ChatMessageGpt;

import com.tecnoplacita.machete.service.ChatServiceGpt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatServiceGptImpl implements ChatServiceGpt {

	
	@Value("${chatgpt.api.url}")
	private String chatGptApiUrl;

	@Value("${chatgpt.api.token}")
	private String token;
	
	
    @Autowired
    private ChatRepositoryGpt chatRepository;

    
    public String handleRequest(MessageRequestGpt request) {
        switch (request.getRequestType()) {
        case PRONUNCIATION:
        	return getPronuntiationSapanishInglesFigurativo(request.getMessage());
        			
            case LEARNING_ASSISTANT:
                return getLearningAssistantResponse(request.getMessage(), request.getTopic());
            case CONVERSATION_SIMULATION:
                return startConversationSimulation(request.getLevel(), request.getTopic());
            case EXERCISE_GENERATOR:
                return generateExercise(request.getTopic());
            case TEXT_CORRECTION:
                return correctText(request.getMessage());
            case PERSONAL_TUTORING:
                return createStudyPlan(request.getTopic());
            case CODE_TRANSLATION:
                return translateCodeComments(request.getMessage());
            case PROJECT_FEEDBACK:
                return provideFeedback(request.getMessage());
            case INFORMAL_CHAT:
                return casualConversation(request.getTopic());
            case TECH_INTERVIEW_SIMULATION:
                return simulateTechnicalInterview(request.getTopic());
            default:
                return "Tipo de solicitud no reconocido.";
        }
    }

    // Implementación de métodos específicos para cada tipo de solicitud

    private String getPronuntiationSapanishInglesFigurativo(String oracion) {
    	return "Traduce la oración proporcionada. Si está en español, tradúcela al inglés con la pronunciación figurada en la segunda línea. Si está en inglés, tradúcela al español sin pronunciación figurada. No agregues texto explicativo. La oración es: " + oracion;

    }
    private String getLearningAssistantResponse(String concept, String topic) {
    	return "Tengo una duda sobre " + topic + ". ¿Podrías explicarme cómo funciona " + concept + " y darme algunos ejemplos?";
      
    }

    private String startConversationSimulation(String level, String topic) {
    	return"Vamos a tener una conversación en inglés. Mi nivel es " + level + ". ¿Puedes hacerme preguntas sobre " + topic + "?";

    }

    private String generateExercise(String topic) {
    	return "Necesito un ejercicio práctico sobre " + topic + " para mejorar mis habilidades. ¿Podrías crear uno para mí?";
     
    }

    private String correctText(String text) {
    	return "Te voy a enviar un texto en inglés. ¿Puedes revisarlo y corregir cualquier error gramatical o de estilo? Aquí está mi texto: '" + text + "'";

    }

    private String createStudyPlan(String area) {
    	return "Quiero mejorar en " + area + ". ¿Podrías crear un plan de estudio para las próximas 4 semanas?";

    }

    private String translateCodeComments(String code) {
    	return"Tengo un fragmento de código con comentarios en español. ¿Podrías traducir los comentarios y explicaciones al inglés? Aquí está mi código: " + code;

    }

    private String provideFeedback(String project) {
    	return "Este es mi proyecto en inglés. ¿Podrías darme feedback y sugerencias de mejora? Aquí está mi proyecto: " + project;
      
    }

    private String casualConversation(String topic) {
    	return "Vamos a tener una conversación casual en inglés. Quiero practicar mi fluidez hablando sobre " + topic + ".";
       
    }

    private String simulateTechnicalInterview(String topic) {
    	return "Quiero practicar una entrevista técnica en inglés. ¿Podrías hacerme preguntas sobre " + topic + " y darme feedback?";

    }

    public String getChatGptResponse(MessageRequestGpt requestMessage) {
        // Primero buscar la respuesta localmente
        ChatMessageGpt chatMessage = chatRepository.findByMessage(requestMessage.getMessage());
        if (chatMessage != null) {
            return chatMessage.getResponse();
        }
        

        String MensajePrompt=handleRequest(requestMessage);
        
        // Si no existe localmente, hacer la consulta a ChatGPT
        String response = fetchFromChatGpt(MensajePrompt);

        System.out.println(response.toString());
        // Guardar la respuesta en la base de datos solo si es seguro
        if (isSafeToStore(response)) {
            ChatMessageGpt newMessage = new ChatMessageGpt();
            newMessage.setMessage(requestMessage.getMessage());
            newMessage.setResponse(response);
           chatRepository.save(newMessage);
        }

        return response;
    }

    private boolean isSafeToStore(String response) {
        // Implementa la lógica para verificar si la respuesta es segura para almacenar
        return response != null && !response.contains("contenido_inapropiado");
    }

    public  String fetchFromChatGpt(String message) {
      
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject request = new JSONObject();
        request.put("model", "gpt-4");
        request.put("messages", Arrays.asList(Map.of("role", "user", "content", message)));

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(chatGptApiUrl, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            JSONObject jsonResponse = new JSONObject(responseBody);
            String chatGptResponse = jsonResponse.getJSONArray("choices")
                                                   .getJSONObject(0)
                                                   .getJSONObject("message")
                                                   .getString("content");
            return chatGptResponse;
        } else {
            return "Error en la respuesta de ChatGPT: " + response.getStatusCode();
        }
    }

	@Override
	public List<ChatMessageGpt> obtenertodo() {
		
		return chatRepository.findAll();
	}
}
