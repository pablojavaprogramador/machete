package com.tecnoplacita.machete.service.impl;

import com.tecnoplacita.machete.exceptions.controlados.EmailAlreadyExistsException;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.UserRepository;
import com.tecnoplacita.machete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
    	  if (userRepository.existsByEmail(user.getEmail())) {
              throw new EmailAlreadyExistsException("El correo electrónico ya está registrado.");
          }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codificar la contraseña antes de guardarla
        return userRepository.save(user);
    }

	@Override
	public User actualizar(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Codificar la contraseña antes de guardarla
        return userRepository.save(user);
	}
}
