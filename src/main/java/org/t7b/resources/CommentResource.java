package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.CommentDTO;
import org.t7b.entities.Comment;
import org.t7b.entities.Post;
import org.t7b.entities.User;
import org.t7b.repositories.CommentRepository;
import org.t7b.repositories.PostRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/comments")
public class CommentResource {
    @Inject
    CommentRepository commentRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PostRepository postRepository;
    
    @GET
    public List<Comment> getAll() {
        return commentRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Comment getById(@PathParam("id") Long id) {
        return commentRepository.findById(id);
    }
    
    @POST
    @Transactional
    public Comment create(CommentDTO commentInput) {
        User user = userRepository.findById(commentInput.getAuthorId());
        Post post = postRepository.findById(commentInput.getPostId());
        
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        
        commentRepository.persist(comment);
        return comment;
    }
    
    @PATCH
    @Path("/{id}")
    public Comment update(@PathParam("id") Long id, Comment updatingComment) {
        Comment existingComment = commentRepository.findById(id);
        
        boolean changed = false;
        
        if (existingComment.getContent() != null) {
            existingComment.setContent(updatingComment.getContent());
            changed = true;
        }
        
        if (changed) {
            existingComment.setUpdatedAt(LocalDateTime.now());
            commentRepository.persist(existingComment);
        }
        
        return existingComment;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public Comment delete(@PathParam("id") Long id) {
        Comment comment = commentRepository.findById(id);
        commentRepository.delete(comment);
        return comment;
    }
}
