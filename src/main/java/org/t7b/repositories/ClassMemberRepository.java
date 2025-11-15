package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.embeddable.ClassMemberId;
import org.t7b.entities.ClassMember;

import java.util.List;

@ApplicationScoped
public class ClassMemberRepository implements PanacheRepository<ClassMember> {
    public List<ClassMember> findByClassId(Long classId) {
        return list("class_id = ?1", classId);
    }
    
    public ClassMember findByClassMemberId(ClassMemberId id) {
        return find("classId = ?1 and studentId = ?2", id.getClassId(), id.getStudentId()).firstResult();
    }
    
}
