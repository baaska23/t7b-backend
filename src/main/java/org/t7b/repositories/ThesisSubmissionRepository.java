package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;
import org.t7b.entities.ClassMember;
import org.t7b.entities.ThesisSubmission;

import java.util.List;

@ApplicationScoped
public class ThesisSubmissionRepository implements PanacheRepository<ThesisSubmission> {
    public List<ThesisSubmission> getHistory(Long studentId) {
        return list("student_id = ?1", studentId);
    }
}
