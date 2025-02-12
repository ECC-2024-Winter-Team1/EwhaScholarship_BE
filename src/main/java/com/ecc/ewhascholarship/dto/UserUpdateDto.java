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
public class UserUpdateDto {

    private String department;

    private float gpa;

    private int year;

    private int incomeLevel;

    public User toEntity() {
        User user = new User();
        user.setId(null);
        user.setUsername(null);
        user.setPassword(null);
        user.setDepartment(this.department);
        user.setGpa(this.gpa);
        user.setYear(this.year);
        user.setIncomeLevel(this.incomeLevel);
        return user;
    }

}
