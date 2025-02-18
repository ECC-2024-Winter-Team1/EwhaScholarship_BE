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

    private String type;

    private String note;

    private Boolean isBookmarked;

    public static ScholarshipDetailDto fromEntity(Scholarship scholarship, boolean isBookmarked) {
        return ScholarshipDetailDto.builder()
                .scholarshipId(scholarship.getId())
                .name(scholarship.getName())
                .amount(scholarship.getAmount())
                .criteria(scholarship.getCriteria())
                .applicationPeriod(scholarship.getApplicationPeriod())
                .paymentPeriod(scholarship.getPaymentPeriod())
                .type(scholarship.getType().toString())
                .note(scholarship.getNote())
                .isBookmarked(isBookmarked)
                .build();
    }

}