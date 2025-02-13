package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.repository.BookmarkRepository;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    // 북마크 조회
    public List<BookmarkDto> bookmarks(String userId) {

        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(UUID.fromString(userId));
        return bookmarkRepository.findByUserId(UUID.fromString(userId))
                .stream()
                .map(bookmark -> BookmarkDto.createBookmarkDto(bookmark))
                .collect(Collectors.toList());
    }

    // 북마크 등록
    @Transactional
    public BookmarkDto addBookmark(String userId, Long scholarshipId) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 사용자입니다."));

        Scholarship scholarship = scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 장학금입니다."));

        if (bookmarkRepository.existsByUserAndScholarship(user, scholarship)) {
            return null;
        }

        Bookmark bookmark = Bookmark.createBookmark(user, scholarship);
        bookmarkRepository.save(bookmark);
        return BookmarkDto.createBookmarkDto(bookmark);
    }

    // 북마크 삭제
    @Transactional
    public BookmarkDto deleteBookmark(String userId, Long scholarshipId) {

        Bookmark target = bookmarkRepository.findById(scholarshipId)
                .orElseThrow(() -> new IllegalArgumentException("북마크 삭제 실패! 북마크 목록에 없는 장학금입니다."));

        bookmarkRepository.delete(target);
        return BookmarkDto.createBookmarkDto(target);
    }
}
