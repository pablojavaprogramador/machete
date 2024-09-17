package com.tecnoplacita.machete.controller;


import com.tecnoplacita.machete.model.Dictionary;
import com.tecnoplacita.machete.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping
    public List<Dictionary> getAllWords() {
        return dictionaryService.getAllWords();
    }

    @GetMapping("/{id}")
    public Optional<Dictionary> getWordById(@PathVariable Long id) {
        return dictionaryService.getWordById(id);
    }

    @PostMapping
    public Dictionary createWord(@RequestBody Dictionary dictionary) {
        return dictionaryService.createWord(dictionary);
    }

    @PutMapping("/{id}")
    public Dictionary updateWord(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        return dictionaryService.updateWord(id, dictionary);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        dictionaryService.deleteWord(id);
    }

    @GetMapping("/definition")
    public Optional<Dictionary> getDefinition(@RequestParam String word) {
        return dictionaryService.getDefinition(word);
    }
}
