package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.CommentReplyDTO;
import org.t7b.entities.Comment;
import org.t7b.entities.CommentReply;
import org.t7b.entities.User;
import org.t7b.repositories.CommentReplyRepository;
import org.t7b.repositories.CommentRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/comment-replies")
public class CommentReplyResource {
    @Inject
    CommentReplyRepository commentReplyRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    CommentRepository commentRepository;
    
    @GET
    public List<CommentReply> getAll() {
        return commentReplyRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public CommentReply getById(@PathParam("id") Long id) {
        return commentReplyRepository.findById(id);
    }
    
    @POST
    @Transactional
    public CommentReply create(CommentReplyDTO commentReplyInput) {
        User user = userRepository.findById(commentReplyInput.getAuthorId());
        
        Comment comment = commentRepository.findById(commentReplyInput.getCommentId());
        
        CommentReply commentReply = new CommentReply();
        commentReply.setAuthor(user);
        commentReply.setComment(comment);
        commentReply.setContent(commentReplyInput.getContent());
        commentReply.setCreatedAt(LocalDateTime.now());
        
        commentReplyRepository.persist(commentReply);
        return commentReply;
    }
    
    @PATCH
    @Path("/{id}")
    public CommentReply update(@PathParam("id") Long id, CommentReply updatingCommentReply) {
        CommentReply commentReply = commentReplyRepository.findById(id);
        
        boolean changed = false;
        
        if (commentReply.getContent() != null) {
            commentReply.setContent(updatingCommentReply.getContent());
            changed = true;
        }
        
        if (changed) {
            commentReply.setUpdatedAt(LocalDateTime.now());
            commentReplyRepository.persist(commentReply);
        }
        
        return commentReply;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public CommentReply delete(@PathParam("id") Long id) {
        CommentReply commentReply = commentReplyRepository.findById(id);
        commentReplyRepository.delete(commentReply);
        return commentReply;
    }
}
