package com.ecc.ewhascholarship.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column
    private String department;

    @Column
    private float gpa;

    @Column
    private int year;

    @Column
    private int incomeLevel;
}
