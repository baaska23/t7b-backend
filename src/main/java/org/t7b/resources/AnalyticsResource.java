package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.t7b.dto.AnalyticsDTO;
import org.t7b.dto.AnalyticsInternalDTO;
import org.t7b.repositories.ThesisSubmissionRepository;
import org.t7b.repositories.UserRepository;

@Path("/api/analytics")
public class AnalyticsResource {
    @Inject
    UserRepository userRepository;
    
    @Inject
    ThesisSubmissionRepository thesisSubmissionRepository;
    
    @GET
    public AnalyticsInternalDTO get() {
        AnalyticsInternalDTO analyticsInternalDTO = new AnalyticsInternalDTO();
        analyticsInternalDTO.setDbUsage(12L);
        analyticsInternalDTO.setStorageUsage(12L);
        return analyticsInternalDTO;
    }
    
    @GET
    @Path("/summary")
    public AnalyticsDTO getSummary() {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setTotalUsers(userRepository.count());
        analyticsDTO.setTotalUsers(thesisSubmissionRepository.count());
        return analyticsDTO;
    }
}
