package com.tecnoplacita.machete.service;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.model.Learning;

import java.util.List;
import java.util.Optional;

public interface LearningService {

    Optional<Learning> getLessonById(Long id);
    Learning createLesson(Learning lesson);
    Learning updateLesson(Long id, Learning lesson);
    void deleteLesson(Long id);
	List<Learning> getAllLessons();
}
