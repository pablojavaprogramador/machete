
package com.tecnoplacita.machete.controller;

import com.tecnoplacita.machete.dto.NotificationDTO;
import com.tecnoplacita.machete.dto.UserDTO;
import com.tecnoplacita.machete.model.Notification;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.UserRepository;
import com.tecnoplacita.machete.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired 
    UserRepository userRepository;

    @GetMapping()
    public List<NotificationDTO> getNotificaciones() {
        List<Notification> notifications = notificationService.getAllNotifications();

        // Convertir la lista de notificaciones a NotificationDTO y UserDTO
        List<NotificationDTO> response = notifications.stream()
            .map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getMessage(),
                new UserDTO(notification.getUser().getId(), notification.getUser().getUsuario(), notification.getUser().getEmail()),
                notification.getType(),
                notification.getCreatedAt(),
                notification.isRead()
            ))
            .collect(Collectors.toList());

        return response;
    }


    @GetMapping("/{id}")
    public Optional<Notification> getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public Notification createNotification(@RequestBody NotificationDTO notificationDTO) {
        // Convertir NotificationDTO a Notification
        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        notification.setRead(notificationDTO.isRead());
        
        // Buscar el usuario en la base de datos por su ID desde el UserDTO
        User user = userRepository.findById(notificationDTO.getUser().getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asignar el usuario a la notificación
        notification.setUser(user);

        // Guardar la notificación
        return notificationService.createNotification(notification);
    }


    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
