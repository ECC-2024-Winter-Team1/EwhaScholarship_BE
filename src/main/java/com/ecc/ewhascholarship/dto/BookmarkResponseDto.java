package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Scholarship;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookmarkResponseDto {

    private Long id;
    private String userId;
    private Scholarship scholarship;
    private LocalDateTime createdAt;
}
