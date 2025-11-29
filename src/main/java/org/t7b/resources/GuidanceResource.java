package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.GuidanceDTO;
import org.t7b.entities.Guidance;
import org.t7b.entities.Post;
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
    
    @GET
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
    
    @POST
    @Transactional
    @Path("/{guidanceId}/like")
    public Response like(@PathParam("guidanceId") Long guidanceId) {
        Guidance guidance = guidanceRepository.findById(guidanceId);
        guidance.setLikeCount(guidance.getLikeCount() + 1);
        return Response.noContent().build();
    }
    
    @POST
    @Transactional
    @Path("/{guidanceId}/dislike")
    public Response dislike(@PathParam("guidanceId") Long guidanceId) {
        Guidance guidance = guidanceRepository.findById(guidanceId);
        guidance.setDislikeCount(guidance.getDislikeCount() + 1);
        return Response.noContent().build();
    }
    
    @POST
    @Transactional
    @Path("/{guidanceId}/undo/{likeType}")
    public Response undo(@PathParam("guidanceId") Long guidanceId, @PathParam("likeType") String likeType) {
        Guidance guidance = guidanceRepository.findById(guidanceId);
        if ("like".equals(likeType)) {
            guidance.setLikeCount(Math.max(0, guidance.getLikeCount() - 1));
        } else if ("dislike".equals(likeType)) {
            guidance.setDislikeCount(Math.max(0, guidance.getDislikeCount() - 1));
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.noContent().build();
    }
}
