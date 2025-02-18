package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.dto.ScholarshipDetailDto;
import com.ecc.ewhascholarship.dto.ScholarshipDto;
import com.ecc.ewhascholarship.dto.ScholarshipSearchRequestDto;
import com.ecc.ewhascholarship.repository.BookmarkRepository;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.specification.ScholarshipSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    // 전체 장학금 조회
    public Page<ScholarshipDto> getScholarships(ScholarshipSearchRequestDto query, UUID userId) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());

        Specification<Scholarship> spec = Specification.where(ScholarshipSpecification.hasSearchKeyword(query.getSearch()))
                .and(ScholarshipSpecification.hasDepartment(query.getDepartment()))
                .and(ScholarshipSpecification.hasYear(query.getYear()))
                .and(ScholarshipSpecification.hasGpa(query.getGpa()))
                .and(ScholarshipSpecification.hasIncomeLevel(query.getIncomeLevel()));

        Page<Scholarship> scholarshipPage = scholarshipRepository.findAll(spec, pageable);

        return scholarshipPage.map(scholarship -> {
            boolean isBookmarked = bookmarkRepository.existsByUserIdAndScholarshipId(userId, scholarship.getId());
            return ScholarshipDto.fromEntity(scholarship, isBookmarked);
        });
    }

    // 특정 장학금 조회
    public ScholarshipDetailDto getScholarshipById(Long scholarshipId, UUID userId) {
        Scholarship scholarship = scholarshipRepository.findById(scholarshipId).orElse(null);

        if (scholarship==null) {
            throw new IllegalArgumentException("해당 장학금을 찾을 수 없습니다.");
        }

        boolean isBookmarked = bookmarkRepository.existsByUserIdAndScholarshipId(userId, scholarshipId);

        return ScholarshipDetailDto.fromEntity(scholarship, isBookmarked);
    }

}
