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
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String department;

    @Column
    private float gpa;

    @Column
    private int year;

    @Column
    private int incomeLevel;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarkList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

    public void patch(User dto) {
        if (dto.getUsername() != null && !dto.getUsername().equals(this.username)) {
            throw new IllegalArgumentException("아이디는 수정할 수 없습니다.");
        }
        if (dto.getPassword() != null) {
            throw new IllegalArgumentException("비밀번호는 수정할 수 없습니다.");
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