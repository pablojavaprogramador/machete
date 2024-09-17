package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Learning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRepository extends JpaRepository<Learning, Long> {
}
