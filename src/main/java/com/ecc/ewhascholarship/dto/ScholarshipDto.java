package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Scholarship;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipDto {

    private Long scholarshipId;

    private String name;

    private String amount;

    private String applicationPeriod;

    private String type;

    public static ScholarshipDto fromEntity(Scholarship scholarship) {
        return ScholarshipDto.builder()
                .scholarshipId(scholarship.getId())
                .name(scholarship.getName())
                .amount(scholarship.getAmount())
                .applicationPeriod(scholarship.getApplicationPeriod())
                .type(scholarship.getType().toString())
                .build();
    }
}