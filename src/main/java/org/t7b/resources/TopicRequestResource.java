package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.TopicRequestDTO;
import org.t7b.entities.StudentClass;
import org.t7b.entities.ThesisTopic;
import org.t7b.entities.TopicRequest;
import org.t7b.entities.User;
import org.t7b.repositories.StudentClassRepository;
import org.t7b.repositories.ThesisTopicRepository;
import org.t7b.repositories.TopicRequestRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/topic-requests")
public class TopicRequestResource {
    @Inject
    TopicRequestRepository topicRequestRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    ThesisTopicRepository thesisTopicRepository;
    
    @Inject
    StudentClassRepository studentClassRepository;
    
    @GET
    public List<TopicRequest> getAll() {
        return topicRequestRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public TopicRequest getById(@PathParam("id") Long id) {
        return topicRequestRepository.findById(id);
    }
    
    @POST
    @Transactional
    public TopicRequest create(TopicRequestDTO topicRequestInput) {
        User student = userRepository.findById(topicRequestInput.getStudentId());
        StudentClass studentClass = studentClassRepository.findById(topicRequestInput.getClassId());
        ThesisTopic topic = thesisTopicRepository.findById(topicRequestInput.getTopicId());
        
        TopicRequest topicRequest = new TopicRequest();
        
        topicRequest.setStudent(student);
        topicRequest.setTopic(topic);
        topicRequest.setStudentClass(studentClass);
        topicRequest.setMessage(topicRequestInput.getMessage());
        topicRequest.setStatus("pending");
        topicRequest.setCreatedAt(LocalDateTime.now());
        
        return topicRequest;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public TopicRequest delete(@PathParam("id") Long id) {
        TopicRequest topicRequest = topicRequestRepository.findById(id);
        topicRequestRepository.delete(topicRequest);
        return topicRequest;
    }
    
    @POST
    @Transactional
    @Path("/{topicId}/{studentId}/approve")
    public Response approve(@PathParam("topicId") Long topicId, @PathParam("studentId") Long studentId) {
        TopicRequest topicRequest = topicRequestRepository.findRequest(topicId, studentId);
        topicRequest.setStatus("approved");
        return Response.noContent().build();
    }
    
    @POST
    @Transactional
    @Path("/{topicId}/{studentId}/reject")
    public Response reject(@PathParam("topicId") Long topicId, @PathParam("studentId") Long studentId) {
        TopicRequest topicRequest = topicRequestRepository.findRequest(topicId, studentId);
        topicRequest.setStatus("rejected");
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{classId}/all-requests")
    public List<TopicRequest> getAllRequest(@PathParam("classId") Long classId) {
        return  topicRequestRepository.findByClassId(classId);
    }
}
