package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "thesis_submissions")
@Getter
@Setter
@NoArgsConstructor
public class ThesisSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long submissionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User student;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private ThesisTopic topic;
    
    private int version;
    
    @Column(name = "thesis_link")
    private String thesisLink;
    
    @Column(name = "feedback")
    private String feedback;
    
    @Column(name = "annonated_link")
    private String annonatedLink;
    
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public ThesisSubmission(Long submissionId, User student, ThesisTopic topic, int version, String thesisLink, String feedback, String annonatedLink, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.submissionId = submissionId;
        this.student = student;
        this.topic = topic;
        this.version = version;
        this.thesisLink = thesisLink;
        this.feedback = feedback;
        this.annonatedLink = annonatedLink;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
