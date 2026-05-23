package com.school.controller;

import com.school.dto.StudentProfileUpdateRequest;
import com.school.entity.Course;
import com.school.entity.Student;
import com.school.security.StudentContext;
import com.school.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentProfileController {

    private final StudentService studentService;
    private final StudentContext studentContext;

    public StudentProfileController(StudentService studentService, StudentContext studentContext) {
        this.studentService = studentService;
        this.studentContext = studentContext;
    }

    @GetMapping("/profile")
    public ResponseEntity<Student> getProfile() {
        Long studentId = studentContext.getCurrentStudentId();
        return ResponseEntity.ok(studentService.getProfile(studentId));
    }

    @PutMapping("/profile")
    public ResponseEntity<Student> updateProfile(@Valid @RequestBody StudentProfileUpdateRequest request) {
        Long studentId = studentContext.getCurrentStudentId();
        return ResponseEntity.ok(studentService.updateProfile(studentId, request));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAssignedCourses(@RequestParam(required = false) String topic) {
        Long studentId = studentContext.getCurrentStudentId();
        return ResponseEntity.ok(studentService.getAssignedCourses(studentId, topic));
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> leaveCourse(@PathVariable Long courseId) {
        Long studentId = studentContext.getCurrentStudentId();
        studentService.leaveCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
