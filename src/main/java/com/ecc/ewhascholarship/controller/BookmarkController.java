package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.dto.BookmarkRequestDto;
import com.ecc.ewhascholarship.dto.ScholarshipDto;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.UserRepository;
import com.ecc.ewhascholarship.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Autowired
    private UserRepository userRepository;

    // 북마크 조회
    @GetMapping("/api/bookmarks")
    public ResponseEntity<ApiResponse<List<ScholarshipDto>>> bookmarks(Principal principal) {

        String userId = principal.getName();
        List<ScholarshipDto> dtos = bookmarkService.getBookmarkedScholarships(UUID.fromString(userId));

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 조회 성공!", dtos));
    }

    // 북마크 등록
    @PostMapping("/api/bookmarks")
    public ResponseEntity<ApiResponse<List<BookmarkDto>>> addBookmark(@RequestBody BookmarkRequestDto dto,
                                                                      Principal principal) {

        String userId = principal.getName();
        BookmarkDto addedDto = bookmarkService.addBookmark(dto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 등록 성공!", null));
    }

    // 북마크 삭제
    @DeleteMapping("/api/bookmarks/{scholarshipId}")
    public ResponseEntity<ApiResponse<List<BookmarkDto>>> deleteBookmark(@PathVariable Long scholarshipId,
                                                                         Principal principal) {

        String userId = principal.getName();
        bookmarkService.deleteBookmark(scholarshipId, userId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 삭제 성공!", null));
    }
}
