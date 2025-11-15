package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.entities.Analytic;
import org.t7b.entities.User;
import org.t7b.repositories.AnalyticRepository;
import org.t7b.repositories.UserRepository;
import org.t7b.services.UserService;

import java.time.Instant;
import java.util.List;

@Path("/api/users")
public class UserResource {
    @Inject
    UserRepository userRepository;
    
    @Inject
    UserService userService;
    
    @Inject
    AnalyticRepository analyticRepository;
    
    @GET
    public List<User> getAll() {
        List<User> users = userRepository.listAll();
        long total = userRepository.count();
        
        Analytic analytic = new Analytic("total_users", total, Instant.now());
        
        analyticRepository.persist(analytic);
        
        return users;
    }
    
    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") Long id) {
        return userRepository.findById(id);
    }
    
    @POST
    @Path("/logout")
    
    public Response logout(@HeaderParam("Authorization") String authHeader) {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
        }
        
        userService.logout(token);
        return Response.noContent().build();
    }
}
