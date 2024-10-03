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


    private static final int MAX_NOTIFICATIONS = 20;
    
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
	        User user = notification.getUser();

	        // Verificar cuántas notificaciones existen para el usuario
	        List<Notification> userNotifications = notificationRepository.findByUserOrderByCreatedAtDesc(user);

	        // Si excede el límite, eliminar las más antiguas
	        if (userNotifications.size() >= MAX_NOTIFICATIONS) {
	            // Determinar cuántas notificaciones eliminar
	            int excess = userNotifications.size() - MAX_NOTIFICATIONS + 1;

	            // Obtener las más antiguas y eliminarlas
	            List<Notification> notificationsToDelete = userNotifications.subList(MAX_NOTIFICATIONS - excess, userNotifications.size());
	            notificationRepository.deleteAll(notificationsToDelete);
	        }

	        // Guardar la nueva notificación
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