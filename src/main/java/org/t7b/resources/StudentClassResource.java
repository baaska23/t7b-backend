package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.t7b.dto.StudentClassDTO;
import org.t7b.entities.StudentClass;
import org.t7b.entities.User;
import org.t7b.repositories.StudentClassRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/classes")
public class StudentClassResource {
    @Inject
    StudentClassRepository studentClassRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public List<StudentClass> getAll() {
        return studentClassRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public StudentClass getById(@PathParam("id") Long id) {
        return studentClassRepository.findById(id);
    }
    
    @GET
    @Path("/professor/{id}")
    public List<StudentClass> getByProfessorId(@PathParam("id") Long id) {
        return studentClassRepository.findByProfessorId(id);
    }
    
    @POST
    @Transactional
    public StudentClass create(StudentClassDTO studentClassInput) {
        User user = userRepository.findById(studentClassInput.getProfessorId());
        
        StudentClass studentClass = new StudentClass();
        studentClass.setProfessor_id(user.getUserId());
        studentClass.setClassName(studentClassInput.getClassName());
        studentClass.setDescription(studentClassInput.getDescription());
        studentClass.setCreatedAt(LocalDateTime.now());
        
        studentClassRepository.persist(studentClass);
        return studentClass;
    }
    
    @PATCH
    @Path("/{id}")
    public StudentClass update(@PathParam("id") Long id, StudentClass updatingStudentClass) {
        StudentClass existingStudentClass = new StudentClass();
        
        if (existingStudentClass.getClassName() != null) {
            existingStudentClass.setClassName(updatingStudentClass.getClassName());
        }
        
        if (existingStudentClass.getDescription() != null) {
            existingStudentClass.setDescription(updatingStudentClass.getDescription());
        }
        
        studentClassRepository.persist(existingStudentClass);
        return existingStudentClass;
    }
    
    @DELETE
    @Path("/{id}")
    public StudentClass delete(@PathParam("id") Long id) {
        StudentClass studentClass = studentClassRepository.findById(id);
        studentClassRepository.delete(studentClass);
        return studentClass;
    }
}
