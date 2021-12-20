package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

     @GetMapping
    public List<Student> getStundent(){
        return studentService.getStudent();
     }


     @PostMapping
    public void addNewStudent(@RequestBody Student newStudent){
        studentService.addNewStudent(newStudent);
     }

     @DeleteMapping(path = "{studentID}")
    public void deletingStudent(@PathVariable("studentID")Long id){
            studentService.deletingStudent(id);
     }

     @PutMapping(path = "{studentID}")
    public void updateStudent(@PathVariable("studentID")Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
    studentService.updateStundent(id, name, email);
       }
}
