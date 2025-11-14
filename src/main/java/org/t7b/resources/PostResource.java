package org.t7b.resources;

import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.PostDTO;
import org.t7b.entities.Post;
import org.t7b.entities.StudentClass;
import org.t7b.entities.User;
import org.t7b.repositories.PostRepository;
import org.t7b.repositories.StudentClassRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/posts")
public class PostResource {
    @Inject
    PostRepository postRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    StudentClassRepository studentClassRepository;
    
    @GET
    public List<Post> getAll() {
        return postRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Post getById(@PathParam("id") Long id) {
        return postRepository.findById(id);
    }
    
    @POST
    @Transactional
    public Post create(PostDTO postInput) {
        User author = userRepository.findById(postInput.getAuthorId());
        StudentClass studentClass = studentClassRepository.findById(postInput.getClassId());
        
        Post post = new Post();
        post.setAuthor(author);
        post.setAClass(studentClass);
        post.setContent(postInput.getContent());
        post.setType(postInput.getType());
        post.setCreatedAt(LocalDateTime.now());
        
        postRepository.persist(post);
        return post;
    }
    
    @PATCH
    @Path("/{id}")
    public Post update(@PathParam("id") Long id, Post updatingPost) {
        Post existingPost = postRepository.findById(id);
        boolean changed = false;
        
        if (existingPost.getContent() != null) {
            existingPost.setContent(updatingPost.getContent());
            changed = true;
        }
        
        if (changed) {
            existingPost.setUpdatedAt(LocalDateTime.now());
            postRepository.persist(existingPost);
        }
        
        return existingPost;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public Post delete(@PathParam("id") Long id) {
        Post post = postRepository.findById(id);
        postRepository.delete(post);
        return post;
    }
}
