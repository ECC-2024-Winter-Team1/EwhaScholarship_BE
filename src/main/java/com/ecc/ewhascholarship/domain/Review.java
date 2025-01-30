package com.ecc.ewhascholarship.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "scholarship_id",nullable = false)
    private Scholarship scholarship;

    @Column
    private Boolean isAwarded;

    @Column
    private Integer applicationYear;

    @Column
    private Integer applicationSemester;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
}
