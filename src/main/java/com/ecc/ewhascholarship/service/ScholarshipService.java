package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.dto.ScholarshipDetailDto;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

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
