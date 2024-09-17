package com.tecnoplacita.machete.repository;

import com.tecnoplacita.machete.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
