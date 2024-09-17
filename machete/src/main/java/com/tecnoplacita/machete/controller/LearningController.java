package com.tecnoplacita.machete.controller;

//LearningController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tecnoplacita.machete.model.Learning;
import com.tecnoplacita.machete.service.LearningService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/learning")
public class LearningController {
	@Autowired
	private LearningService learningService;

	@GetMapping("/lessons")
	public List<Learning> getAllLessons() {
		return learningService.getAllLessons();
	}

	@GetMapping("/lessons/{id}")
	public Optional<Learning> getLessonById(@PathVariable Long id) {
		return learningService.getLessonById(id);
	}

	@PostMapping("/lessons")
	public Learning createLesson(@RequestBody Learning learning) {
		return learningService.createLesson(learning);
	}

	@PutMapping("/lessons/{id}")
	public Learning updateLesson(@PathVariable Long id, @RequestBody Learning learning) {
		return learningService.updateLesson(id, learning);
	}

	@DeleteMapping("/lessons/{id}")
	public void deleteLesson(@PathVariable Long id) {
		learningService.deleteLesson(id);
	}
}
