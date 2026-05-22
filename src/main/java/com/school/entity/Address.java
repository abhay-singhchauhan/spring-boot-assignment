package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.enums.AddressType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType type;  // PERMANENT, CURRENT, CORRESPONDENCE

    private String street;
    private String city;
    private String state;
    private String pincode;

    // Many addresses belong to one student
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore  // prevents infinite loop in JSON response!
    private Student student;
}

