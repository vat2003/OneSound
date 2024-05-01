package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteAlbumId;
import com.project.shopapp.entity.FavoriteAlbum;

public interface FavoriteAlbumDAO extends JpaRepository<FavoriteAlbum, FavoriteAlbumId> {
    List<FavoriteAlbum> findByUserId(Long userId);

    FavoriteAlbum findByUserIdAndAlbumId(Long userId, Long albumId);
}
