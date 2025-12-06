package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.TemplateDTO;
import org.t7b.dto.TemplateUploadForm;
import org.t7b.entities.Template;
import org.t7b.entities.User;
import org.t7b.repositories.TemplateRepository;
import org.t7b.repositories.UserRepository;
import org.t7b.services.S3Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/templates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TemplateResource {
    @Inject
    TemplateRepository templateRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    S3Service s3Service;
    
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
    @Transactional
    public Template update(@PathParam("id") Long id, Template updatingTemplate) {
        Template existingTemplate = templateRepository.findById(id);
        
        if (updatingTemplate.getTitle() != null) {
            existingTemplate.setTitle(updatingTemplate.getTitle());
        }
        
        if (updatingTemplate.getTemplateLink() != null) {
            existingTemplate.setTemplateLink(updatingTemplate.getTemplateLink());
        }
        
        templateRepository.persist(existingTemplate);
        return existingTemplate;
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Template delete(@PathParam("id") Long id) {
        Template template = templateRepository.findById(id);
        templateRepository.delete(template);
        return template;
    }
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadTemplate(
            TemplateUploadForm form
    ) {
        User user = userRepository.findById(form.authorId);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User not found")
                    .build();
        }
        
        String objectKey = "templates/"
                + UUID.randomUUID()
                + "_" + form.fileName;
        
        s3Service.upload(
                objectKey,
                form.file,
                form.fileSize,
                form.contentType
        );
        
        String fileUrl = objectKey;
        
        Template template = new Template();
        template.setUploadedBy(user);
        template.setTemplateLink(fileUrl);
        template.setTitle(form.title);
        template.setCreatedAt(LocalDateTime.now());
        
        templateRepository.persist(template);
        
        return Response.created(URI.create(fileUrl))
                .entity(template)
                .build();
    }
}