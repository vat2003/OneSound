package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.entity.SongSinger;

public interface SongAuthorDAO extends JpaRepository<SongAuthor, SongAuthorId> {
    List<SongAuthor> findByAuthorId(Long singer);

    List<SongAuthor> findBySongId(Long singer);

    //    void deleteBySongId(long id);
    void deleteBySongId(Long albumId);
}
