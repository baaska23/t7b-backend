package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.t7b.embeddable.ClassMemberId;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_members")
@Getter
@Setter
@NoArgsConstructor
public class ClassMember {
    @EmbeddedId
    private ClassMemberId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    private StudentClass aClass;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private User student;
    
    @Column(name = "joined_at")
    private LocalDateTime joinedAt = LocalDateTime.now();
    
    public ClassMember(ClassMemberId id, StudentClass aClass, User student, LocalDateTime joinedAt) {
        this.id = id;
        this.aClass = aClass;
        this.student = student;
        this.joinedAt = joinedAt;
    }
}
