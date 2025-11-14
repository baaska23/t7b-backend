package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.TopicRequestDTO;
import org.t7b.entities.ThesisTopic;
import org.t7b.entities.TopicRequest;
import org.t7b.entities.User;
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
        
        ThesisTopic topic = thesisTopicRepository.findById(topicRequestInput.getTopicId());
        
        TopicRequest topicRequest = new TopicRequest();
        
        topicRequest.setStudent(student);
        topicRequest.setTopic(topic);
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
}
