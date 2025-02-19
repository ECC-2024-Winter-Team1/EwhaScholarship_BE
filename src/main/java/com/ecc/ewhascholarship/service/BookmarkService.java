package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.domain.Scholarship;
import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.dto.BookmarkRequestDto;
import com.ecc.ewhascholarship.dto.ScholarshipDto;
import com.ecc.ewhascholarship.repository.BookmarkRepository;
import com.ecc.ewhascholarship.repository.ScholarshipRepository;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    // 북마크 조회
    public List<ScholarshipDto> getBookmarkedScholarships(UUID userId) {

        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
        return bookmarks
                .stream()
                .map(bookmark -> ScholarshipDto.fromEntity(bookmark.getScholarship(), true))
                .toList();
    }

    // 북마크 등록
    @Transactional
    public BookmarkDto addBookmark(BookmarkRequestDto dto, String userId) {

        Scholarship scholarship = scholarshipRepository.findById(dto.getScholarshipId())
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 장학금입니다.") {
                });

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("북마크 등록 실패! 존재하지 않는 사용자입니다."));

        if (bookmarkRepository.existsByUserIdAndScholarshipId(UUID.fromString(userId), dto.getScholarshipId())) {
            throw new DuplicateKeyException("이미 북마크한 장학금입니다.");
        }

        Bookmark bookmark = Bookmark.createBookmark(dto, user, scholarship);
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
