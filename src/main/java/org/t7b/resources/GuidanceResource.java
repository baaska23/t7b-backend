package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.GuidanceDTO;
import org.t7b.entities.Guidance;
import org.t7b.entities.User;
import org.t7b.repositories.GuidanceRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/guidances")
public class GuidanceResource {
    @Inject
    GuidanceRepository guidanceRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public List<Guidance> getAll() {
        return guidanceRepository.listAll();
    }
    
    @Path("/{id}")
    public Guidance getById(@PathParam("id") Long id) {
        return guidanceRepository.findById(id);
    }
    
    @POST
    @Transactional
    public Guidance create(GuidanceDTO guidanceInput) {
        User user = userRepository.findById(guidanceInput.getAuthorId());
        
        Guidance guidance = new Guidance();
        guidance.setUploadedBy(user);
        guidance.setTitle(guidanceInput.getTitle());
        guidance.setContent(guidanceInput.getContent());
        guidance.setCreatedAt(LocalDateTime.now());
        
        guidanceRepository.persist(guidance);
        return guidance;
    }
    
    @PATCH
    @Path("/{id}")
    public Guidance update(@PathParam("id") Long id, Guidance updatingGuidance) {
        Guidance existingGuidance = guidanceRepository.findById(id);
        
        if (existingGuidance.getContent() != null) {
            existingGuidance.setContent(updatingGuidance.getContent());
        }
        
        if (existingGuidance.getTitle() != null) {
            existingGuidance.setTitle(updatingGuidance.getTitle());
        }
        
        guidanceRepository.persist(existingGuidance);
        return existingGuidance;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public Guidance delete(@PathParam("id") Long id) {
        Guidance guidance = guidanceRepository.findById(id);
        guidanceRepository.delete(guidance);
        return guidance;
    }
}
