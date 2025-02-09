package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BookmarkDto {
    private Long scholarshipId;
    private String name;
    private String amount;
    private String applicationPeriod;
    private String type;

    public static BookmarkDto createBookmarkDto(Bookmark bookmark) {
        return new BookmarkDto(
                bookmark.getScholarship().getId(),
                bookmark.getScholarship().getName(),
                bookmark.getScholarship().getAmount(),
                bookmark.getScholarship().getApplicationPeriod(),
                bookmark.getScholarship().getType().name()
        );
    }
}
