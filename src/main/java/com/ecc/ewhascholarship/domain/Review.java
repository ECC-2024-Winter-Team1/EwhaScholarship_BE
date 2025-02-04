package com.ecc.ewhascholarship.domain;

import com.ecc.ewhascholarship.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id",nullable = false)
    private Scholarship scholarship;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Boolean isAwarded;

    @Column
    private Integer applicationYear;

    @Column
    private Integer applicationGrade;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    public static Review createReview(ReviewDto dto, Scholarship scholarship, User user) {

        if (dto.getId() != null) {
            throw new IllegalArgumentException("리뷰 등록 실패! 리뷰의 id가 없어야 합니다.");
        }
        if (dto.getScholarshipId() != scholarship.getId()) {
            throw new IllegalArgumentException("리뷰 등록 실패! 장학금의 id가 잘못됐습니다.");
        }
        if (dto.getUserId() != user.getId()) {
            throw new IllegalArgumentException("리뷰 등록 실패! 사용자의 id가 잘못됐습니다.");
        }

        return new Review(
                null,  // id는 자동 생성됨
                scholarship,    // ?
                user,
                dto.getIsAwarded(),
                dto.getApplicationYear(),
                dto.getApplicationGrade(),
                dto.getContent()
        );
    }

    public void patch(ReviewDto dto) {

        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("리뷰 수정 실패! 리뷰의 id가 잘못됐습니다.");
        }
        if (!scholarship.getId().equals(getScholarship().getId())) {
            throw new IllegalArgumentException("리뷰 수정 실패! 장학금의 id가 잘못됐습니다.");
        }
        if (!user.getId().equals(getUser().getId())) {
            throw new IllegalArgumentException("리뷰 수정 실패! 사용자의 id가 잘못됐습니다.");
        }

        if (dto.getIsAwarded() != null) {
            this.isAwarded = dto.getIsAwarded();
        }
        if (dto.getApplicationYear() != null) {
            this.applicationYear = dto.getApplicationYear();
        }
        if (dto.getApplicationGrade() != null) {
            this.applicationGrade = dto.getApplicationGrade();
        }
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }
    }
}