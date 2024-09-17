package com.tecnoplacita.machete.service.impl;

import com.tecnoplacita.machete.model.Learning;
import com.tecnoplacita.machete.repository.LearningRepository;
import com.tecnoplacita.machete.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningServiceImpl implements LearningService {

    @Autowired
    private LearningRepository learningRepository;




    @Override
    public Learning createLesson(Learning lesson) {
        return learningRepository.save(lesson);
    }

    @Override
    public Learning updateLesson(Long id, Learning lesson) {
        if (learningRepository.existsById(id)) {
            lesson.setId(id);
            return learningRepository.save(lesson);
        }
        return null;
    }

    @Override
    public void deleteLesson(Long id) {
        learningRepository.deleteById(id);
    }

	@Override
	public List<Learning> getAllLessons() {
		
		return learningRepository.findAll();
	}



	@Override
	public Optional<Learning> getLessonById(Long id) {
		
		return  learningRepository.findById(id);
	}
}
