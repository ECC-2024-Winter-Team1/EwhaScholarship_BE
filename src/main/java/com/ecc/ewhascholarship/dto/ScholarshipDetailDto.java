package com.ecc.ewhascholarship.dto;


import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.domain.enums.ScholarshipType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipDetailDto {

    private Long scholarshipId;

    private String name;

    private String amount;

    private String criteria;

    private String applicationPeriod;

    private String paymentPeriod;

    private ScholarshipType type;

    private String note;

    public static ScholarshipDetailDto fromEntity(Scholarship scholarship) {
        return ScholarshipDetailDto.builder()
                .scholarshipId(scholarship.getId())
                .name(scholarship.getName())
                .amount(scholarship.getAmount())
                .criteria(scholarship.getCriteria())
                .applicationPeriod(scholarship.getApplicationPeriod())
                .paymentPeriod(scholarship.getPaymentPeriod())
                .type(scholarship.getType())
                .note(scholarship.getNote())
                .build();
    }

}