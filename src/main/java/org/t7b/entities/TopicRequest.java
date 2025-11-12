package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topic_requests")
@Getter
@Setter
@NoArgsConstructor
public class TopicRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private ThesisTopic topic;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User student;
    
    private String message;
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
