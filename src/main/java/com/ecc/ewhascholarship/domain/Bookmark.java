package com.ecc.ewhascholarship.domain;

import com.ecc.ewhascholarship.dto.BookmarkDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Bookmark createBookmark(BookmarkDto dto, Scholarship scholarship, User user) {

        if (dto.getScholarshipId() != scholarship.getId()) {
            throw new IllegalArgumentException("북마크 등록 실패! 장학금의 id가 잘못됐습니다.");
        }

        return new Bookmark(
                null,
                user,
                scholarship,
                dto.getCreatedAt()
        );
    }
}