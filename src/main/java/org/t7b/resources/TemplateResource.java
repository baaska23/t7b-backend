package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.TemplateDTO;
import org.t7b.entities.Template;
import org.t7b.entities.User;
import org.t7b.repositories.TemplateRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/templates")
public class TemplateResource {
    @Inject
    TemplateRepository templateRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public List<Template> getAll() {
        return templateRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Template getById(@PathParam("id") Long id) {
        return templateRepository.findById(id);
    }
    
    @POST
    @Transactional
    public Template create(TemplateDTO templateInput) {
        User user = userRepository.findById(templateInput.getAuthorId());
        
        Template template = new Template();
        template.setUploadedBy(user);
        template.setTemplateLink(templateInput.getTemplateLink());
        template.setTitle(templateInput.getTitle());
        template.setCreatedAt(LocalDateTime.now());
        
        templateRepository.persist(template);
        return template;
    }
    
    @PATCH
    @Path("/{id}")
    public Template update(@PathParam("id") Long id, Template updatingTemplate) {
        Template existingTemplate = templateRepository.findById(id);
        
        if (existingTemplate.getTitle() != null) {
            existingTemplate.setTitle(updatingTemplate.getTitle());
        }
        
        if (existingTemplate.getTemplateLink() != null) {
            existingTemplate.setTemplateLink((updatingTemplate.getTemplateLink()));
        }
        
        templateRepository.persist(existingTemplate);
        return existingTemplate;
    }
    
    @DELETE
    @Path("/{id}")
    public Template delete(@PathParam("id") Long id) {
        Template template = templateRepository.findById(id);
        templateRepository.delete(template);
        return template;
    }
}
