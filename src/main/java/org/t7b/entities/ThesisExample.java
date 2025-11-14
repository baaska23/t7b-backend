package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "theses_repository")
@Getter
@Setter
@NoArgsConstructor
public class ThesisExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_id")
    private Long thesisId;
    
    @Column(name = "thesis_link")
    private String thesisLink;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    private String title;
    private int grade;
    
    private LocalDateTime uploaded = LocalDateTime.now();
    
    public ThesisExample(Long thesisId, String thesisLink, User approvedBy, User author, String title, int grade, LocalDateTime uploaded) {
        this.thesisId = thesisId;
        this.thesisLink = thesisLink;
        this.approvedBy = approvedBy;
        this.author = author;
        this.title = title;
        this.grade = grade;
        this.uploaded = uploaded;
    }
}
