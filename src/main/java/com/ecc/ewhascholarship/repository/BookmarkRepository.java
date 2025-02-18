package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(UUID userId);
    boolean existsByUserIdAndScholarshipId(UUID userId, Long scholarshipId);
    Optional<Bookmark> findByUserIdAndScholarshipId(UUID userId, Long scholarshipId);
}
