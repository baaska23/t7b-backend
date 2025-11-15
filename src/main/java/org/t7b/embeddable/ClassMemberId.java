package org.t7b.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ClassMemberId {
    @Column(name = "class_id")
    private Long classId;
    
    @Column(name = "student_id")
    private Long studentId;
    
    public ClassMemberId(Long classId, Long studentId) {
    }
}
