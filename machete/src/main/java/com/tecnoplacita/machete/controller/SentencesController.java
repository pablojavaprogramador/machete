package com.tecnoplacita.machete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecnoplacita.machete.model.Sentence;
import com.tecnoplacita.machete.service.SentenceService;

import java.util.List;

@RestController
@RequestMapping("/api/sentences")
public class SentencesController {

    @Autowired
    private SentenceService sentenceService;

    // Obtener todas las oraciones
    @GetMapping
    public ResponseEntity<List<Sentence>> getAllSentences() {
        List<Sentence> sentences = sentenceService.getAllSentences();
        return new ResponseEntity<>(sentences, HttpStatus.OK);
    }

    // Obtener una oración por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sentence> getSentenceById(@PathVariable Long id) {
        Sentence sentence = sentenceService.getSentenceById(id);
        return sentence != null ? 
            new ResponseEntity<>(sentence, HttpStatus.OK) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Crear una nueva oración
    @PostMapping
    public ResponseEntity<Sentence> createSentence(@RequestBody Sentence sentence) {
        Sentence createdSentence = sentenceService.createSentence(sentence);
        return new ResponseEntity<>(createdSentence, HttpStatus.CREATED);
    }

    // Actualizar una oración existente
    @PutMapping("/{id}")
    public ResponseEntity<Sentence> updateSentence(@PathVariable Long id, @RequestBody Sentence sentence) {
        Sentence updatedSentence = sentenceService.updateSentence(id, sentence);
        return updatedSentence != null ? 
            new ResponseEntity<>(updatedSentence, HttpStatus.OK) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar una oración por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSentence(@PathVariable Long id) {
        boolean isDeleted = sentenceService.deleteSentence(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
