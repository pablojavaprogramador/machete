package com.tecnoplacita.machete.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnoplacita.machete.model.Verb;

import java.util.List;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Long> {
    List<Verb> findByBaseFormContainingIgnoreCase(String baseForm);
}
