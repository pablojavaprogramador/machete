package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUsername(String username);
}
