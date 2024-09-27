package com.tecnoplacita.machete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnoplacita.machete.model.Sentence;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
   
}
