package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.ScholarshipDetailDto;
import com.ecc.ewhascholarship.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping("/{scholarshipId}")
    public ResponseEntity<ApiResponse<ScholarshipDetailDto>> getScholarship(@PathVariable Long scholarshipId) {
        ScholarshipDetailDto response = scholarshipService.getScholarshipById(scholarshipId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("특정 장학금 조회 성공!", response));
    }
}
