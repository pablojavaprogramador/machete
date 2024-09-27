package com.tecnoplacita.machete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.model.Notification;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.NotificationRepository;
import com.tecnoplacita.machete.repository.UserRepository;
import com.tecnoplacita.machete.service.NotificationService;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private UserRepository userRepository; // Repositorio de usuario
	
	@Override
	public List<Notification> getAllNotifications() {
		
		return notificationRepository.findAll();
	}

	@Override
	public Optional<Notification> getNotificationById(Long id) {
		
		return notificationRepository.findById(id);
	}

	public Notification createNotification(Notification notification) {
	    return notificationRepository.save(notification);
	}


	@Override
	public Notification updateNotification(Long id ,Notification notification) {
		notification.setId(id);
		return notificationRepository.save(notification);
	}

	@Override
	public void deleteNotification(Long id) {
		 notificationRepository.deleteById(id);
		
	}




   

}