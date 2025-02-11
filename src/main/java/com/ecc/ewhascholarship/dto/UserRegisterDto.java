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
public class UserRegisterDto {

    private UUID id;

    private String username;

    private String password;

    private String department;

    private float gpa;

    private int year;

    private int incomeLevel;

    public User toEntity(String encodedPassword) {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(encodedPassword);
        user.setDepartment(this.department);
        user.setGpa(this.gpa);
        user.setYear(this.year);
        user.setIncomeLevel(this.incomeLevel);
        return user;
    }

}
