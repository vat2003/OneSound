package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteSongId;
import com.project.shopapp.entity.FavoriteSong;

public interface FavoriteSongDAO extends JpaRepository<FavoriteSong, FavoriteSongId> {
    List<FavoriteSong> findByUserId(Long userId);

}
