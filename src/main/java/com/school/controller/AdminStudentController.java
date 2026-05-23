package com.school.controller;
import com.school.entity.Student;
import com.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/admin")
@RestController
public class AdminStudentController {
    private final StudentService studentService;

    public AdminStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity <List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.searchAllStudents());
    }

    @GetMapping("/students/search")
    public ResponseEntity<List<Student>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.searchByName(name));
    }

    @GetMapping("/students/by-course/{courseId}")
    public ResponseEntity<List<Student>> searchByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.searchByCourse(courseId));
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Student> assignCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.assignCourse(studentId, courseId));
    }

}
