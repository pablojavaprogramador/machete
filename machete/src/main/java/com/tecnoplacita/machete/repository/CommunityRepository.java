package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
