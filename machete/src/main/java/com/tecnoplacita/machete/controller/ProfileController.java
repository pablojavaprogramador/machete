package com.tecnoplacita.machete.controller;

//ProfileController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tecnoplacita.machete.model.Profile;
import com.tecnoplacita.machete.service.ProfileService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@GetMapping
	public List<Profile> getAllProfiles() {
		return profileService.getAllProfiles();
	}

	@GetMapping("/{id}")
	public Optional<Profile> getProfileById(@PathVariable Long id) {
		return profileService.getProfileById(id);
	}

	@PostMapping
	public Profile createProfile(@RequestBody Profile profile) {
		return profileService.createProfile(profile);
	}

	@PutMapping("/{id}")
	public Profile updateProfile(@RequestBody Profile profile) {
		return profileService.updateProfile(profile);
	}

	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable Long id) {
		profileService.deleteprofile(id);
	}
}