package com.ecc.ewhascholarship.domain;

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
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String department;

    @Column
    private float gpa;

    @Column
    private int year;

    @Column
    private int incomeLevel;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bookmark> bookmarkList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviewList;

    public void patch(User dto) {
        if (dto.getEmail() != null && !dto.getEmail().equals(this.email)) {
            throw new IllegalArgumentException("이메일은 수정할 수 없습니다.");
        }
        if (dto.getDepartment() != null) {
            this.department = dto.getDepartment();
        }
        if (dto.getGpa() >= 0) {
            this.gpa = dto.getGpa();
        }
        if (dto.getYear() > 0) {
            this.year = dto.getYear();
        }
        if (dto.getIncomeLevel() >= 0) {
            this.incomeLevel = dto.getIncomeLevel();
        }
    }
}