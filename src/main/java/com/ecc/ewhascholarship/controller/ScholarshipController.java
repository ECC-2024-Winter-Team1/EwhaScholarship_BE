package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.ScholarshipDetailDto;
import com.ecc.ewhascholarship.dto.ScholarshipDto;
import com.ecc.ewhascholarship.dto.ScholarshipSearchRequestDto;
import com.ecc.ewhascholarship.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ScholarshipDto>>> getScholarships(@ModelAttribute ScholarshipSearchRequestDto query) {
        Page<ScholarshipDto> scholarships = scholarshipService.getScholarships(query);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("장학금 조회 성공!", scholarships));
    }

    @GetMapping("/{scholarshipId}")
    public ResponseEntity<ApiResponse<ScholarshipDetailDto>> getScholarshipById(@PathVariable Long scholarshipId) {
        ScholarshipDetailDto response = scholarshipService.getScholarshipById(scholarshipId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("특정 장학금 조회 성공!", response));
    }
}
