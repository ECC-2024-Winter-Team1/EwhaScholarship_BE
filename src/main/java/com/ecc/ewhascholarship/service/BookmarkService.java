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
    public BookmarkDto addBookmark(Long scholarshipId, BookmarkDto dto, String userId) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 사용자입니다."));

        Scholarship scholarship = scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 장학금입니다."));

        if (bookmarkRepository.existsByUserAndScholarship(user, scholarship)) {
            throw new IllegalArgumentException("이미 북마크한 장학금입니다.");
        }

        Bookmark bookmark = Bookmark.createBookmark(dto, scholarship, user);
        Bookmark added = bookmarkRepository.save(bookmark);
        return BookmarkDto.createBookmarkDto(added);
    }

    // 북마크 삭제
    @Transactional
    public void deleteBookmark(Long scholarshipId, String userId) {

        Bookmark bookmark = bookmarkRepository.findByUserIdAndScholarshipId(UUID.fromString(userId), scholarshipId)
                .orElseThrow(() -> new IllegalArgumentException("북마크 삭제 실패! 해당 북마크가 존재하지 않습니다."));

        bookmarkRepository.delete(bookmark);
    }
}
