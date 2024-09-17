package com.tecnoplacita.machete.service;

import java.util.List;
import java.util.Optional;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.model.Profile;

public interface ProfileService {

	List<Profile> getAllProfiles();

	Optional<Profile> getProfileById(Long id);

	Profile createProfile(Profile profile);

	Profile updateProfile(Profile profile);

	void deleteprofile(Long id);

	

}
