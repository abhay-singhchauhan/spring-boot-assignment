package com.school.repository;

import com.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStudents_Id(Long studentId);

    List<Course> findByStudents_IdAndTopicsContainingIgnoreCase(Long studentId, String topic);
}
