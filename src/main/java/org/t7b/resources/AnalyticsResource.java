package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.t7b.entities.Analytic;
import org.t7b.repositories.AnalyticRepository;

import java.util.List;

@Path("/api/analytics")
public class AnalyticsResource {
    @Inject
    AnalyticRepository analyticRepository;
    
    @GET
    public List<Analytic> getAnalytics() {
        return analyticRepository.listAll();
    }
}
