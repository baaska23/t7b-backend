package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.t7b.dto.ThesisSubmissionDTO;
import org.t7b.entities.ThesisSubmission;
import org.t7b.entities.ThesisTopic;
import org.t7b.entities.User;
import org.t7b.repositories.ThesisSubmissionRepository;
import org.t7b.repositories.ThesisTopicRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/thesis-submissions")
public class ThesisSubmissionResource {
    @Inject
    ThesisSubmissionRepository thesisSubmissionRepository;
    
    @Inject
    UserRepository userRepository;
    @Inject
    ThesisTopicRepository thesisTopicRepository;
    
    @GET
    public List<ThesisSubmission> getAll() {
        return thesisSubmissionRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public ThesisSubmission getById(@PathParam("id") Long id) {
        return thesisSubmissionRepository.findById(id);
    }
    
    @POST
    @Transactional
    public ThesisSubmission create(ThesisSubmissionDTO thesisSubmissionInput) {
        User student = userRepository.findById(thesisSubmissionInput.getStudentId());
        
        ThesisTopic thesisTopic = thesisTopicRepository.findById(thesisSubmissionInput.getTopicId());
        
        ThesisSubmission thesisSubmission = new ThesisSubmission();
        thesisSubmission.setStudent(student);
        thesisSubmission.setTopic(thesisTopic);
        thesisSubmission.setThesisLink(thesisSubmissionInput.getThesisLink());
//        thesisSubmission.setVersion(thesisSubmissionInput.getVersion());
        thesisSubmission.setFeedback(thesisSubmissionInput.getFeedback());
        thesisSubmission.setCreatedAt(LocalDateTime.now());
//        thesisSubmission.setStatus("submitted");
        
        return thesisSubmission;
    }
    
    @GET
    @Path("/history/{studentId}")
    public List<ThesisSubmission> getHistory(@PathParam("studentId") Long studentId) {
        return thesisSubmissionRepository.getHistory(studentId);
    }
}
