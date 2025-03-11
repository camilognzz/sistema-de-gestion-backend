package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.Student;
import com.fundacion_habacuc.sistema_de_gestion.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll(){
        return studentService.getStudents();
    }

    @GetMapping("/{studentId}")
    public Optional<Student> getById(@PathVariable("studentId") Long studentId){
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Student student){
        studentService.savOrUpdate(student);
    }

    @DeleteMapping("/{studentId}")
    public void getAll(@PathVariable("studentId") Long studentId){
        studentService.delete(studentId);
    }
}
