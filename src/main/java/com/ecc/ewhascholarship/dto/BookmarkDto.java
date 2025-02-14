package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Bookmark;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkDto {

    private Long id;
    private String userId;
    private Long scholarshipId;
    private LocalDateTime createdAt;

    public static BookmarkDto createBookmarkDto(Bookmark bookmark) {

        return new BookmarkDto(
                bookmark.getId(),
                bookmark.getUser().getId().toString(),
                bookmark.getScholarship().getId(),
                bookmark.getCreatedAt()
        );
    }
}
