package com.tecnoplacita.machete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnoplacita.machete.model.Sentence;
import com.tecnoplacita.machete.repository.SentenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;

    // Obtener todas las oraciones
    public List<Sentence> getAllSentences() {
        return sentenceRepository.findAll();
    }

    // Obtener una oración por ID
    public Sentence getSentenceById(Long id) {
        Optional<Sentence> sentence = sentenceRepository.findById(id);
        return sentence.orElse(null); // Devuelve null si no se encuentra
    }

    // Crear una nueva oración
    public Sentence createSentence(Sentence sentence) {
        return sentenceRepository.save(sentence);
    }

    // Actualizar una oración existente
    public Sentence updateSentence(Long id, Sentence sentence) {
        if (sentenceRepository.existsById(id)) {
            sentence.setId(id); // Asegúrate de establecer el ID
            return sentenceRepository.save(sentence);
        }
        return null; // Devuelve null si no se encuentra
    }

    // Eliminar una oración por ID
    public boolean deleteSentence(Long id) {
        if (sentenceRepository.existsById(id)) {
            sentenceRepository.deleteById(id);
            return true;
        }
        return false; // Devuelve false si no se encuentra
    }
}
