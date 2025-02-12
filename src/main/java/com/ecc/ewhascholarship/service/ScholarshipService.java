package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.dto.ScholarshipDetailDto;
import com.ecc.ewhascholarship.dto.ScholarshipDto;
import com.ecc.ewhascholarship.dto.ScholarshipSearchRequestDto;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    // 전체 장학금 조회
    public Page<ScholarshipDto> getScholarships(ScholarshipSearchRequestDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());

        Page<Scholarship> scholarshipPage = scholarshipRepository.findAll(pageable);

        return scholarshipPage.map(ScholarshipDto::fromEntity);
    }

    // 특정 장학금 조회
    public ScholarshipDetailDto getScholarshipById(Long scholarshipId) {
        Scholarship scholarship = scholarshipRepository.findById(scholarshipId).orElse(null);
        System.out.println(scholarship.toString());

        if (scholarship==null) {
            throw new IllegalArgumentException("해당 장학금을 찾을 수 없습니다.");
        }

        return ScholarshipDetailDto.fromEntity(scholarship);
    }

}
