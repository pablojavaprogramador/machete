package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}
