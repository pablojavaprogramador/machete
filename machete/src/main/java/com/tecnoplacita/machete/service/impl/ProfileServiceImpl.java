package com.tecnoplacita.machete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnoplacita.machete.model.Profile;
import com.tecnoplacita.machete.repository.ProfileRepository;
import com.tecnoplacita.machete.service.ProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

	@Override
	public List<Profile> getAllProfiles() {
		
		return profileRepository.findAll();
	}

	@Override
	public Optional<Profile> getProfileById(Long id) {
		// TODO Auto-generated method stub
		return profileRepository.findById(id);
	}

	@Override
	public Profile createProfile(Profile profile) {
		// TODO Auto-generated method stub
		return profileRepository.save(profile);
	}

	@Override
	public Profile updateProfile(Profile profile) {
		
		return profileRepository.save(profile);
	}

	@Override
	public void deleteprofile(Long id) {
		 profileRepository.deleteById(id);
		
	}

   

}