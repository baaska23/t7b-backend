package org.t7b;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyEntityRepository implements PanacheRepository<MyEntity> {
    // Custom query methods can be added here
}
