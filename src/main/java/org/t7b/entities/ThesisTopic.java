package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "theses_topics")
@Getter
@Setter
@NoArgsConstructor
public class ThesisTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private User professor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private StudentClass aClass;
    
    private String title;
    private String description;
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public ThesisTopic(Long topicId, User professor, StudentClass aClass, String title, String description, String status, LocalDateTime createdAt) {
        this.topicId = topicId;
        this.professor = professor;
        this.aClass = aClass;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }
}
