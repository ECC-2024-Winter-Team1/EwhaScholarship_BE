package com.ecc.ewhascholarship.domain;

import jakarta.persistence.*;

@Entity
public class ScholarshipYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @Column(nullable = false)
    private Integer year;
}
