package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteGenresId;
import com.project.shopapp.entity.FavoriteGenre;

public interface FavoriteGenresDAO extends JpaRepository<FavoriteGenre, FavoriteGenresId> {

}
