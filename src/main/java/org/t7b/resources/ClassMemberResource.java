package org.t7b.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.t7b.dto.ClassMemberDTO;
import org.t7b.embeddable.ClassMemberId;
import org.t7b.entities.ClassMember;
import org.t7b.entities.StudentClass;
import org.t7b.entities.User;
import org.t7b.repositories.ClassMemberRepository;
import org.t7b.repositories.StudentClassRepository;
import org.t7b.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/class-members")
public class ClassMemberResource {
    @Inject
    ClassMemberRepository classMemberRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    StudentClassRepository studentClassRepository;
    
    @GET
    public List<ClassMember> getAll() {
        return classMemberRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public ClassMember getById(@PathParam("id") Long id) {
        return classMemberRepository.findById(id);
    }
    
    @POST
    @Transactional
    public ClassMember add(ClassMemberDTO classMemberInput) {
        User student = userRepository.findById(classMemberInput.getStudentId());
        
        StudentClass studentClass = studentClassRepository.findById(classMemberInput.getClassId());
        
        ClassMember classMember = new ClassMember();
        classMember.setStudent(student);
        classMember.setAClass(studentClass);
        classMember.setJoinedAt(LocalDateTime.now());
        
        return classMember;
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public ClassMember remove(@PathParam("id") Long id) {
        ClassMember classMember = classMemberRepository.findById(id);
        classMemberRepository.delete(classMember);
        return classMember;
    }
    
    @GET
    @Path("/{id}/all-students")
    public List<ClassMember> getAllStudents(@PathParam("id") Long classId) {
        return classMemberRepository.findByClassId(classId);
    }
    
//    @DELETE
//    @Path("/{classId}/{studentId}")
//    @Transactional
//    public Response leaveClass(@PathParam("classId") Long classId, @PathParam("studentId") Long studentId) {
//        ClassMemberId id = new ClassMemberId(classId, studentId);
//        ClassMember classMember = classMemberRepository.findById(id);
//        classMemberRepository.delete(classMember);
//        return Response.noContent().build();
//    }
}
