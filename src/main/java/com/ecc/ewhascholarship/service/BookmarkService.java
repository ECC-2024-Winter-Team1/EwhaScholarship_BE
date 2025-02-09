package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import com.ecc.ewhascholarship.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<BookmarkDto> bookmarks(String userId) {

        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(UUID.fromString(userId));
        return bookmarkRepository.findByUserId(UUID.fromString(userId))
                .stream()
                .map(bookmark -> BookmarkDto.createBookmarkDto(bookmark))
                .collect(Collectors.toList());
    }
}
