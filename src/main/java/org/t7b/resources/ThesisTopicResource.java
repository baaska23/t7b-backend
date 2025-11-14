package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.ThesisTopicDTO;
import org.t7b.entities.StudentClass;
import org.t7b.entities.ThesisTopic;
import org.t7b.entities.User;
import org.t7b.repositories.StudentClassRepository;
import org.t7b.repositories.ThesisTopicRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/thesis-topic")
public class ThesisTopicResource {
    @Inject
    ThesisTopicRepository thesisTopicRepository;
    
    @Inject
    UserRepository userRepository;
    @Inject
    StudentClassRepository studentClassRepository;
    
    @GET
    public List<ThesisTopic> getAll() {
        return thesisTopicRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public ThesisTopic getById(@PathParam("id") Long id) {
        return thesisTopicRepository.findById(id);
    }
    
    @POST
    @Transactional
    public ThesisTopic create(ThesisTopicDTO thesisTopicInput) {
        User professor = userRepository.findById(thesisTopicInput.getProfessorId());
        
        StudentClass studentClass = studentClassRepository.findById(thesisTopicInput.getClassId());
        
        ThesisTopic thesisTopic = new ThesisTopic();
        thesisTopic.setProfessor(professor);
        thesisTopic.setAClass(studentClass);
        thesisTopic.setDescription(thesisTopicInput.getDescription());
        thesisTopic.setStatus("pending");
        thesisTopic.setCreatedAt(LocalDateTime.now());
        
        return thesisTopic;
    }
    
    @PATCH
    @Path("/{id}/status")
    public ThesisTopic update(@PathParam("id") Long id) {
        ThesisTopic thesisTopic = thesisTopicRepository.findById(id);
        
        boolean approved = false;
        if (!approved) {
            thesisTopic.setStatus("rejected");
        } else thesisTopic.setStatus("approved");
        
        return thesisTopic;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public ThesisTopic delete(@PathParam("id") Long id) {
        ThesisTopic thesisTopic = thesisTopicRepository.findById(id);
        thesisTopicRepository.delete(thesisTopic);
        return thesisTopic;
    }
}
