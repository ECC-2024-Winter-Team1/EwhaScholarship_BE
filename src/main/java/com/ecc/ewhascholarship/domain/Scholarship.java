package com.ecc.ewhascholarship.domain;

import com.ecc.ewhascholarship.domain.enums.ScholarshipType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String amount;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String criteria;

    @Column(nullable = false)
    private String applicationPeriod;

    @Column(nullable = false)
    private String paymentPeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScholarshipType type;

    @Column
    private String field;

    @OneToMany(mappedBy = "scholarship", fetch = FetchType.LAZY)
    private List<Criteria> criteriaList;

    @OneToMany(mappedBy = "scholarship", fetch = FetchType.LAZY)
    private List<Bookmark> bookmarkList;

    @OneToMany(mappedBy = "scholarship", fetch = FetchType.LAZY)
    private List<Review> reviewList;

}
