package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Album;

public interface AlbumDAO extends JpaRepository<Album, Long> {
    List<Album> findByTitleContainingIgnoreCase(String title);

    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT a FROM Album a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Album> searchByTitle(String title, Pageable pageable);

    @Query("SELECT a FROM Album a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Album> searchByTitle(String title);

    @Query("SELECT a FROM Album a WHERE a.active = true")
    List<Album> findAllActiveAlbums();

    @Query("SELECT a FROM Album a WHERE a.active = false")
    List<Album> findAllInactiveAlbums();

    @Query("select COUNT(a) from Album a")
    List<Integer> staticAlbum();
}