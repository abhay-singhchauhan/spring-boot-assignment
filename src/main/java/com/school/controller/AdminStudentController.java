package com.school.controller;
import com.school.entity.Student;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminStudentController {
    private final StudentService studentService;

    public AdminStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity <List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.searchAllStudents());
    }
    
}
