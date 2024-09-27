package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {


	
	
	    Optional<User> findByEmail(String email); // Método que devuelve Optional<User>

		boolean existsByEmail(String email);
		
		 Optional<User>findById(Integer id); // Método que devuelve Optional<User>
	

}
