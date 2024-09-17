package com.tecnoplacita.machete.controller;


import com.tecnoplacita.machete.model.Community;
import com.tecnoplacita.machete.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@GetMapping
	public List<Community> getAllCommunities() {
		return communityService.getAllCommunities();
	}

	@GetMapping("/{id}")
	public Optional<Community> getCommunityById(@PathVariable Long id) {
		return communityService.getCommunityById(id);
	}

	@PostMapping
	public Community createCommunity(@RequestBody Community community) {
		return communityService.createCommunity(community);
	}

	@PutMapping("/{id}")
	public Community updateCommunity(@PathVariable Long id, @RequestBody Community community) {
		return communityService.updateCommunity(id, community);
	}

	@DeleteMapping("/{id}")
	public void deleteCommunity(@PathVariable Long id) {
		communityService.deleteCommunity(id);
	}
}
