package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student newStudent) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(newStudent.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email exists");
        }
        studentRepository.save(newStudent);
    }

    public void deletingStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Student id" + id + "not exists.");
        }
        studentRepository.deleteById(id);

    }
    @Transactional
     public void updateStundent(Long studentID,
                               String name,
                               String email) {
        Student studentMethod = studentRepository.findById(studentID).
                orElseThrow(()->new IllegalStateException("Student with + "+ studentID + "does not exists" ));
        if (name != null && name.length()>0 && !Objects.equals(studentMethod.getName(),name)){
            studentMethod.setName(name);
        }
        if (email != null && email.length()>0 && !Objects.equals(studentMethod.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if (studentOptional.isPresent()){
            throw  new IllegalStateException("email is taken");
        }
        studentMethod.setEmail(email);
        }
    }
}
