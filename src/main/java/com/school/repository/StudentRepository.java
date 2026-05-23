package com.school.repository;

import com.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByCourses_Id(Long courseId);

    boolean existsByStudentCode(String studentCode);

    Optional<Student> findByStudentCodeAndDateOfBirth(String studentCode, LocalDate dateOfBirth);
}
