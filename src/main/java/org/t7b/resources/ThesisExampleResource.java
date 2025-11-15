package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.ThesisExampleDTO;
import org.t7b.entities.ThesisExample;
import org.t7b.entities.User;
import org.t7b.repositories.ThesisExampleRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/thesis-examples")
public class ThesisExampleResource {
    @Inject
    ThesisExampleRepository thesisExampleRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public List<ThesisExample> getAll() {
        return thesisExampleRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public ThesisExample getById(@PathParam("id") Long id) {
        return thesisExampleRepository.findById(id);
    }
    
    @POST
    @Transactional
    public ThesisExample create(ThesisExampleDTO thesisExampleInput) {
        User user = userRepository.findById(thesisExampleInput.getUserId());
        
        User approvedBy = userRepository.findById(thesisExampleInput.getApprovedById());
        
        ThesisExample thesisExample = new ThesisExample();
        
        thesisExample.setAuthor(user);
        thesisExample.setApprovedBy(approvedBy);
        thesisExample.setGrade(thesisExampleInput.getGrade());
        thesisExample.setThesisLink(thesisExampleInput.getThesisLink());
        thesisExample.setTitle(thesisExampleInput.getTitle());
        thesisExample.setUploaded(LocalDateTime.now());
        
        return thesisExample;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public ThesisExample delete(@PathParam("id") Long id) {
        ThesisExample thesisExample = thesisExampleRepository.findById(id);
        thesisExampleRepository.delete(thesisExample);
        return thesisExample;
    }
    
    @POST
    @Transactional
    @Path("/{thesisId}/rate")
    public Response rate(@PathParam("thesisId") Long thesisId, @QueryParam("grade") Integer grade) {
        ThesisExample thesisExample = thesisExampleRepository.findById(thesisId);
        thesisExample.setGrade(grade);
        return Response.ok(thesisExample).build();
    }
}
