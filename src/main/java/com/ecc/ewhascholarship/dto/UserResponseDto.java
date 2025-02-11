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
public class UserResponseDto {

    private UUID id;

    private String username;

    private String department;

    private float gpa;

    private int year;

    private int incomeLevel;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .department(user.getDepartment())
                .gpa(user.getGpa())
                .year(user.getYear())
                .incomeLevel(user.getIncomeLevel())
                .build();
    }

}
