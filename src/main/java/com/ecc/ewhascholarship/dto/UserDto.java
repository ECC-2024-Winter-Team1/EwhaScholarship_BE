package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String email;
    private String department;
    private float gpa;
    private int year;
    private int incomeLevel;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .department(user.getDepartment())
                .gpa(user.getGpa())
                .year(user.getYear())
                .incomeLevel(user.getIncomeLevel())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .department(department)
                .gpa(gpa)
                .year(year)
                .incomeLevel(incomeLevel)
                .build();
    }

}
