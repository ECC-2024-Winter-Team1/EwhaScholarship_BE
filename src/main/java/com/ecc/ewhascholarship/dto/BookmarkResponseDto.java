package com.ecc.ewhascholarship.dto;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.domain.Scholarship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponseDto {

    private Long id;
    private String userId;
    private ScholarshipDto scholarship;

    public static BookmarkResponseDto fromEntity(Bookmark bookmark) {

        return new BookmarkResponseDto(
                bookmark.getId(),
                bookmark.getUser().getId().toString(),
                ScholarshipDto.fromEntity(bookmark.getScholarship(), true)
        );
    }
}
