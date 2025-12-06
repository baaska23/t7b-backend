package org.t7b.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.ThesisSubmissionDTO;
import org.t7b.entities.ThesisExample;
import org.t7b.entities.ThesisSubmission;
import org.t7b.entities.ThesisTopic;
import org.t7b.entities.User;
import org.t7b.repositories.ThesisSubmissionRepository;
import org.t7b.repositories.ThesisTopicRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/theses-submissions")
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
    
    @POST
    @Transactional
    @RolesAllowed("professor")
    @Path("/{submissionId}/feedback")
    public Response rate(@PathParam("submissionId") Long submissionId, @QueryParam("feedback") String feedback) {
        ThesisSubmission thesisSubmission = thesisSubmissionRepository.findById(submissionId);
        thesisSubmission.setFeedback(feedback);
        thesisSubmissionRepository.persist(thesisSubmission);
        return Response.ok(thesisSubmission).build();
    }
}
