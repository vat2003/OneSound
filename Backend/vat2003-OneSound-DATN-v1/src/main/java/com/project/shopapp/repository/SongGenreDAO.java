package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.SongGenre;

public interface SongGenreDAO extends JpaRepository<SongGenre, SongGenreId> {
	List<SongGenre> findByGenreId(Long singer);
	List<SongGenre> findBySongId(Long singer);
	void deleteBySongId(Long albumId);
}
