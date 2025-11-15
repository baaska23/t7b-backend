package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.TopicRequest;

import java.util.List;

@ApplicationScoped
public class TopicRequestRepository implements PanacheRepository<TopicRequest> {
    public TopicRequest findRequest(Long topicId, Long studentId) {
        return find("topic_id = ?1 and student_id = ?2", topicId, studentId).firstResult();
    }
    
    public List<TopicRequest> findByClassId(Long classId) {
        return list("class_id = ?1", classId);
    }
}
