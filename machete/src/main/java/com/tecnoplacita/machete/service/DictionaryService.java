package com.tecnoplacita.machete.service;

import java.util.List;
import java.util.Optional;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.model.Dictionary;

public interface DictionaryService {

	List<Dictionary> getAllWords();

	Optional<Dictionary> getWordById(Long id);

	Dictionary updateWord(Long id, Dictionary dictionary);

	void deleteWord(Long id);

	Dictionary createWord(Dictionary dictionary);

	Optional<Dictionary> getDefinition(String word);
  
}
