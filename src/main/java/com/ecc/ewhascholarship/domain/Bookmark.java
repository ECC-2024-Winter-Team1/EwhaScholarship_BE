package com.ecc.ewhascholarship.domain;

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

    public static Bookmark createBookmark(User user, Scholarship scholarship) {

        Bookmark bookmark = new Bookmark();
        bookmark.user = user;
        bookmark.scholarship = scholarship;
        return bookmark;
    }

    public static Bookmark deleteBookmark(User user, Scholarship scholarship) {

        Bookmark bookmark = new Bookmark();
        bookmark.user = user;
        bookmark.scholarship = scholarship;
        return bookmark;
    }
}