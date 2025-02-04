package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.ReviewDto;
import com.ecc.ewhascholarship.service.ReviewService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 특정 장학금 리뷰 조회
    @GetMapping("/api/scholarships/{scholarshipId}/reviews")
    public ResponseEntity<ApiResponse<List<ReviewDto>>> reviews(@PathVariable Long scholarshipId) {

        List<ReviewDto> dtos = reviewService.reviews(scholarshipId);

        if (dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("해당 리뷰를 찾을 수 없습니다."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("리뷰 조회 성공!", dtos));
    }

    // 리뷰 등록
    @PostMapping("/api/scholarships/{scholarshipId}/reviews")
    public ResponseEntity<ApiResponse<ReviewDto>> create(@PathVariable Long scholarshipId,
                                                         @RequestBody ReviewDto dto) {

        if (dto.getScholarshipId() == null) {
            throw new IllegalArgumentException("리뷰 등록 실패! scholarshipID가 필요합니다.");
        }
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("리뷰 등록 실패! userId가 필요합니다.");
        }

        ReviewDto createdDto = reviewService.create(scholarshipId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("리뷰 등록 성공!", createdDto));
    }

    // 리뷰 수정
    @PatchMapping("/api/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewDto>> update(@PathVariable Long reviewId,
                                                         @RequestBody ReviewDto dto) {

        ReviewDto updatedDto = reviewService.update(reviewId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("리뷰 수정 성공!", updatedDto));

    }

    // 리뷰 삭제
    @DeleteMapping("/api/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewDto>> delete(@PathVariable Long reviewId) {

        ReviewDto deletedDto = reviewService.delete(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("리뷰 삭제 성공!", deletedDto));
    }
}
