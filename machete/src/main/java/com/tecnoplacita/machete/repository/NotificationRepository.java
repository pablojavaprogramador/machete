package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Notification;
import com.tecnoplacita.machete.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	  List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
