package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.Comment;

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {
}
