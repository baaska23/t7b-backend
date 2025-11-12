package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;
    
    @Column(name = "professor_id")
    private Long professor_id;
    
    @Column(name = "class_name")
    private String className;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public StudentClass(Long classId, Long professor_id, String className, String description, LocalDateTime createdAt) {
        this.classId = classId;
        this.professor_id = professor_id;
        this.className = className;
        this.description = description;
        this.createdAt = createdAt;
    }
}
