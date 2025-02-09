package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Bookmark;
import com.ecc.ewhascholarship.dto.BookmarkDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(UUID userId);
}
