package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.NotificationDTO;
import org.t7b.entities.Notification;
import org.t7b.entities.User;
import org.t7b.repositories.NotificationRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/notifications")
public class NotificationResource {
    @Inject
    NotificationRepository notificationRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public List<Notification> getAll() {
        return notificationRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Notification getById(@PathParam("id") Long id) {
        return notificationRepository.findById(id);
    }
    
    @POST
    @Transactional
    public Notification create(NotificationDTO notificationInput) {
        User user = userRepository.findById(notificationInput.getUserId());
        
        Notification notification = new Notification();
        notification.setType(notificationInput.getType());
        notification.setUser(user);
        notification.setContent(notificationInput.getContent());
        notification.setTitle(notificationInput.getTitle());
        notification.setCreatedAt(LocalDateTime.now());
        
        notificationRepository.persist(notification);
        return notification;
    }
    
    @PATCH
    @Path("/{id}/mark-as-read")
    public Notification markAsRead(@PathParam("id") Long id) {
        Notification existingNotification = notificationRepository.findById(id);
        
        if (!existingNotification.isRead()) {
            existingNotification.setRead(true);
        }
        notificationRepository.persist(existingNotification);
        return existingNotification;
    }
    
    @PATCH
    @Path("/mark-as-all-read")
    public List<Notification> markAllAsRead() {
        List<Notification> notifications = notificationRepository.findAll().list();
        
        for (Notification notif : notifications) {
            if (!notif.isRead()) {
                notif.setRead(true);
            }
        }
        notificationRepository.persist(notifications);
        return notifications;
    }
}
