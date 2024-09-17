
package com.tecnoplacita.machete.service.impl;

import com.tecnoplacita.machete.model.Dictionary;
import com.tecnoplacita.machete.repository.DictionaryRepository;
import com.tecnoplacita.machete.service.DictionaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    public List<Dictionary> getAllWords() {
        return dictionaryRepository.findAll();
    }

    public Optional<Dictionary> getWordById(Long id) {
        return dictionaryRepository.findById(id);
    }

    public Dictionary createWord(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public Dictionary updateWord(Long id, Dictionary dictionary) {
        if (dictionaryRepository.existsById(id)) {
            dictionary.setId(id);
            return dictionaryRepository.save(dictionary);
        }
        return null;
    }

    public void deleteWord(Long id) {
        dictionaryRepository.deleteById(id);
    }

    public Optional<Dictionary> getDefinition(String word) {
        return dictionaryRepository.findAll().stream()
                .filter(dict -> dict.getWord().equalsIgnoreCase(word))
                .findFirst();
    }
}
