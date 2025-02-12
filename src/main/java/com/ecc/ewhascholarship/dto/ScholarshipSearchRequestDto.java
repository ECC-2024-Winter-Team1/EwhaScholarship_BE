package com.ecc.ewhascholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipSearchRequestDto {
    private int page = 0;
    private int limit = 10;
    private String search;
    private String department;
    private Integer year;
    private Float gpa;
    private Integer incomeLevel;
}
