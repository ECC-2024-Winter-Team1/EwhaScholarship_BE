package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Review;
import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.ReviewDto;
import com.ecc.ewhascholarship.repository.ReviewRepository;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Autowired
    private UserRepository userRepository;

    // 특정 장학금 리뷰 조회
    public List<ReviewDto> reviews(Long scholarshipId) {

        return reviewRepository.findByScholarshipId(scholarshipId)
                .stream()
                .map(review -> ReviewDto.createReviewDto(review))
                .collect(Collectors.toList());
    }

    // 리뷰 등록
    @Transactional
    public ReviewDto create(Long scholarshipId, ReviewDto dto) {

        Scholarship scholarship = scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰 등록 실패! 존재하지 않는 장학금입니다."));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("리뷰 등록 실패! 존재하지 않는 사용자입니다."));

        Review review = Review.createReview(dto, scholarship, user);
        Review created = reviewRepository.save(review);
        return ReviewDto.createReviewDto(created);
    }

    // 리뷰 수정
    @Transactional
    public ReviewDto update(Long reviewId, ReviewDto dto) {

        Review target = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰 수정 실패! 해당 리뷰가 없습니다."));

        target.patch(dto);
        Review updated = reviewRepository.save(target);
        return ReviewDto.createReviewDto(updated);
    }

    // 리뷰 삭제
    @Transactional
    public ReviewDto delete(Long reviewId) {

        Review target = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰 삭제 실패! 해당 리뷰가 없습니다."));

        reviewRepository.delete(target);
        return ReviewDto.createReviewDto(target);
    }
}
