package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "guidances")
@Getter
@Setter
@NoArgsConstructor
public class Guidance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guidance_id")
    private Long guidanceId;
    
    private String title;
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
    
    @Column(name = "is_read")
    private boolean isRead = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Guidance(Long guidanceId, String title, String content, User uploadedBy, boolean isRead, LocalDateTime createdAt) {
        this.guidanceId = guidanceId;
        this.title = title;
        this.content = content;
        this.uploadedBy = uploadedBy;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
