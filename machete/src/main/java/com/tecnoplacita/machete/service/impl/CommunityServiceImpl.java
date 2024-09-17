package com.tecnoplacita.machete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnoplacita.machete.model.Community;
import com.tecnoplacita.machete.repository.CommunityRepository;
import com.tecnoplacita.machete.service.CommunityService;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Optional<Community> getCommunityById(Long id) {
        return communityRepository.findById(id);
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Community updateCommunity(Long id, Community community) {
        community.setId(id);
        return communityRepository.save(community);
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }


}
