package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReviewDto {

    private Long id;
    private UUID userId;
    private Long scholarshipId;
    private String scholarshipName;
    private String content;
    private Boolean isAwarded;
    private Integer applicationYear;
    private Integer applicationSemester;

    public static ReviewDto createReviewDto(Review review) {

        return new ReviewDto(
                review.getId(),
                review.getUser().getId(),
                review.getScholarship().getId(),
                review.getScholarship().getName(),
                review.getContent(),
                review.getIsAwarded(),
                review.getApplicationYear(),
                review.getApplicationSemester()
        );
    }
}
