package com.ecc.ewhascholarship.domain;

import jakarta.persistence.*;

@Entity
public class ScholarshipIncomeLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @Column(nullable = false)
    private Integer incomeLevel;

}

