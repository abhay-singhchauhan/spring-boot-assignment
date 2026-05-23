package com.school.service;

import com.school.dto.AddressRequest;
import com.school.dto.StudentProfileUpdateRequest;
import com.school.entity.Address;
import com.school.entity.Course;
import com.school.entity.Student;
import com.school.repository.CourseRepository;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> searchAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> searchByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found");
        }
        return studentRepository.findByCourses_Id(courseId);
    }

    public Student addStudent(Student student){
        if(studentRepository.existsByStudentCode(student.getStudentCode())){
            throw new RuntimeException("Student Code already exists");
        }
        if (student.getAddresses() != null) {
            student.getAddresses().forEach(address -> address.setStudent(student));
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student assignCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean alreadyAssigned = student.getCourses().stream()
                .anyMatch(c -> c.getId().equals(courseId));
        if (alreadyAssigned) {
            throw new RuntimeException("Course already assigned to this student");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);
        return studentRepository.save(student);
    }

    public Student getProfile(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Transactional
    public Student updateProfile(Long studentId, StudentProfileUpdateRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.email() != null) {
            student.setEmail(request.email());
        }
        if (request.mobileNumber() != null) {
            student.setMobileNumber(request.mobileNumber());
        }
        if (request.fatherName() != null) {
            student.setFatherName(request.fatherName());
        }
        if (request.motherName() != null) {
            student.setMotherName(request.motherName());
        }
        if (request.addresses() != null) {
            student.getAddresses().clear();
            request.addresses().forEach(addressRequest -> {
                Address address = toAddress(addressRequest);
                address.setStudent(student);
                student.getAddresses().add(address);
            });
        }

        return studentRepository.save(student);
    }

    public List<Course> getAssignedCourses(Long studentId, String topic) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }
        if (topic == null || topic.isBlank()) {
            return courseRepository.findByStudents_Id(studentId);
        }
        return courseRepository.findByStudents_IdAndTopicsContainingIgnoreCase(studentId, topic.trim());
    }

    @Transactional
    public void leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean enrolled = student.getCourses().removeIf(c -> c.getId().equals(courseId));
        if (!enrolled) {
            throw new RuntimeException("Course is not assigned to this student");
        }
        course.getStudents().removeIf(s -> s.getId().equals(studentId));
        studentRepository.save(student);
    }

    private Address toAddress(AddressRequest request) {
        Address address = new Address();
        address.setType(request.type());
        address.setStreet(request.street());
        address.setCity(request.city());
        address.setState(request.state());
        address.setPincode(request.pincode());
        return address;
    }
}
