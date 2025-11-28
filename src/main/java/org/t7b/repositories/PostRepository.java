package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.Post;

import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {
    public List<Post> findByClassId(Long classId) {
        return find("aClass.id = ?1", classId).list();
    }
}
