package com.tecnoplacita.machete.service;

import java.util.List;
import java.util.Optional;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.model.Community;

public interface CommunityService {

	List<Community> getAllCommunities();

	Optional<Community> getCommunityById(Long id);

	Community createCommunity(Community community);

	Community updateCommunity(Long id, Community community);

	void deleteCommunity(Long id);


   
}
