package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    private String description;
    private String courseType;
    private String duration;
    private String topics;

    // Mirror side of the ManyToMany — no @JoinTable here
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore  // prevents infinite loop!
    private List<Student> students = new ArrayList<>();
}
