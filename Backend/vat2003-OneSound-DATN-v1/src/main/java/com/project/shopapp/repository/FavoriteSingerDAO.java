package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteSingerId;
import com.project.shopapp.entity.FavoriteSinger;

public interface FavoriteSingerDAO extends JpaRepository<FavoriteSinger, FavoriteSingerId> {

}
