package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.TopicRequest;

@ApplicationScoped
public class TopicRequestRepository implements PanacheRepository<TopicRequest> {
}
