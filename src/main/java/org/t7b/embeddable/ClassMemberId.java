package org.t7b.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ClassMemberId {
    @Column(name = "class_id")
    private Long classId;
    
    @Column(name = "student_id")
    private Long studentId;
}
