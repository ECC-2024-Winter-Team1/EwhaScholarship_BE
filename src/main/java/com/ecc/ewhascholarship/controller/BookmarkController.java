package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.UserRepository;
import com.ecc.ewhascholarship.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<ApiResponse<List<BookmarkDto>>> bookmarks(Principal principal) {

        String userId = principal.getName();
        List<BookmarkDto> dtos = bookmarkService.bookmarks(userId);

        if (dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("북마크 목록이 없습니다."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 조회 성공!", dtos));
    }

    // 북마크 등록
    @PostMapping("/api/bookmarks/{scholarshipId}")
    public ResponseEntity<ApiResponse<BookmarkDto>> addBookmark(@PathVariable Long scholarshipId,
                                                                @RequestBody BookmarkDto dto,
                                                                Principal principal) {

        String userId = principal.getName();
        BookmarkDto addedDto = bookmarkService.addBookmark(scholarshipId, dto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 등록 성공!", addedDto));
    }

    // 북마크 삭제
    @DeleteMapping("/api/bookmarks/{scholarshipId}")
    public ResponseEntity<ApiResponse<BookmarkDto>> deleteBookmark(@PathVariable Long scholarshipId) {

        BookmarkDto deletedDto = bookmarkService.deleteBookmark(scholarshipId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .success("북마크 삭제 성공!", deletedDto));
    }
}
