package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByScholarshipId(Long scholarshipId);
    List<Review> findByUserId(UUID userId);
}
