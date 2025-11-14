package org.t7b.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.t7b.entities.Guidance;

@ApplicationScoped
public class GuidanceRepository implements PanacheRepository<Guidance> {
}
