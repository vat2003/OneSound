package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteSongId;
import com.project.shopapp.composite.FavoriteYoutubeId;
import com.project.shopapp.entity.FavoriteSong;
import com.project.shopapp.entity.FavoriteYoutube;

public interface FavoriteYoutubeDAO extends JpaRepository<FavoriteYoutube, FavoriteYoutubeId> {
    List<FavoriteYoutube> findByUserId(Long userId);
}
