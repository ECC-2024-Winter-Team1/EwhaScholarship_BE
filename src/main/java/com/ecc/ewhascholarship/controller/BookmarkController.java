package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

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
}
