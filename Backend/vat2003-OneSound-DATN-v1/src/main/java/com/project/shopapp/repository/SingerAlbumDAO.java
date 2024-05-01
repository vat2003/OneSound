package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.SingerAlbum;
import com.project.shopapp.entity.Singer;


public interface SingerAlbumDAO extends JpaRepository<SingerAlbum, SingerAlbumId> {

    List<SingerAlbum> findAllByAlbumId(Long albumId);

    List<SingerAlbum> findBySingerId(Long singer);
    List<SingerAlbum> findByAlbumId(Long singer);
    void deleteByAlbumId(Long albumId);
}
