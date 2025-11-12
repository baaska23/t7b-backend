package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    private String username;
    private String email;
    
    @Column(name = "hashed_password")
    private String hashedPassword;
    
    @Column(name = "user_role")
    private String userRole;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public User(Long userId, String username, String email, String hashedPassword, String userRole, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }
}
