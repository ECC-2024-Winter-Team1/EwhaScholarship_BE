package com.ecc.ewhascholarship.domain;

import com.ecc.ewhascholarship.dto.BookmarkRequestDto;
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

    public static Bookmark createBookmark(BookmarkRequestDto dto, User user, Scholarship scholarship) {
        return new Bookmark(
                null,
                user,
                scholarship,
                LocalDateTime.now()
        );
    }

}