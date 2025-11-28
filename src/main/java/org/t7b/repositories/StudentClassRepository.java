package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.StudentClass;

import java.util.List;

@ApplicationScoped
public class StudentClassRepository implements PanacheRepository<StudentClass> {
    public List<StudentClass> findByProfessorId(Long professorId) {
        return find("professor_id = ?1", professorId).list();
    }
}
