package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private String title;
    private String content;
    private String type;
    
    @Column(name = "is_read")
    private boolean isRead = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Notification(Long notificationId, User user, String title, String content, String type, boolean isRead, LocalDateTime createdAt) {
        this.notificationId = notificationId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
