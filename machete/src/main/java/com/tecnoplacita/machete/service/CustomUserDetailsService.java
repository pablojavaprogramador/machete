package com.tecnoplacita.machete.service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Asegúrate de tener un repositorio para acceder a usuarios

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        User user = userOptional.get();
        
        // Verifica el estado habilitado del usuario
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("Favor de verificar el Correo");
        }

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), 
            user.getPassword(), 
            new ArrayList<>()
        );
    }
}
