package com.ecc.ewhascholarship.domain;

import jakarta.persistence.*;

@Entity
public class ScholarshipGpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @Column(nullable = false)
    private Float gpa;
}
