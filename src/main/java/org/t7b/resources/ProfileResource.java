package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.t7b.entities.Profile;
import org.t7b.repositories.ProfileRepository;

import java.util.List;

@Path("/api/profiles")
public class ProfileResource {
    @Inject
    ProfileRepository profileRepository;
    
    @GET
    public List<Profile> getAll() {
        return profileRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Profile getById(@PathParam("id") Long id) {
        return profileRepository.findById(id);
    }
    
    @PATCH
    @Path("/{id}")
    public Profile updateProfileById(@PathParam("id") Long id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id);
        
        if (existingProfile.getMajor() != null) {
            existingProfile.setMajor(updatedProfile.getMajor());
        }
        
        if (existingProfile.getTeamsAddress() != null) {
            existingProfile.setTeamsAddress((updatedProfile.getTeamsAddress()));
        }
        
        if (existingProfile.getUniversityBranch() != null) {
            existingProfile.setUniversityBranch(updatedProfile.getUniversityBranch());
        }
        
        profileRepository.persist(existingProfile);
        return existingProfile;
    }
}
