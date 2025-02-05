package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 장학금 리뷰 조회
    @Query(value = "SELECT * FROM review WHERE scholarship_id = :scholarshipId",
            nativeQuery = true)
    List<Review> findByScholarshipId(Long scholarshipId);

}
