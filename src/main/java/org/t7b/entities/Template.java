package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "templates")
@Getter
@Setter
@NoArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateId;
    
    private String title;
    
    @Column(name = "template_link")
    private String templateLink;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
    
    @Column(name = "is_read")
    private boolean isRead;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Template(Long templateId, String title, String templateLink, User uploadedBy, boolean isRead, LocalDateTime createdAt) {
        this.templateId = templateId;
        this.title = title;
        this.templateLink = templateLink;
        this.uploadedBy = uploadedBy;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
