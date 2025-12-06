package org.t7b.dto;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.reactive.PartType;

import java.io.InputStream;

public class TemplateUploadForm {
    @FormParam("file")
    @PartType("application/octet-stream")
    public InputStream file;
    
    @FormParam("title")
    @PartType("text/plain")
    public String title;
    
    @FormParam("authorId")
    @PartType("text/plain")
    public Long authorId;
    
    @FormParam("contentType")
    @PartType("text/plain")
    public String contentType;
    
    @FormParam("fileName")
    @PartType("text/plain")
    public String fileName;
    
    @FormParam("fileSize")
    @PartType("text/plain")
    public Long fileSize; // <-- Add this line
}
