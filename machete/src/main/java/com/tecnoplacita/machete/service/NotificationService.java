package com.tecnoplacita.machete.service;

import com.tecnoplacita.machete.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

	

	Optional<Notification> getNotificationById(Long id);

	Notification createNotification(Notification notification);

	Notification updateNotification(Long id, Notification notification);

	void deleteNotification(Long id);

	List<Notification> getAllNotifications();

	

}
