package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.Singer;

public interface SingerDAO extends JpaRepository<Singer, Long> {
    List<Singer> findByActiveTrue();
    Singer findByFullnameIgnoreCaseContaining(String firstName);

    @Query("select s from Singer s join s.singerAlbums sa join sa.album a where a.id = :albumId")
    List<Singer> findAllSingerByAlbumId(Long albumId);

    @Query("SELECT a FROM Singer a WHERE LOWER(a.fullname) LIKE LOWER(CONCAT('%', :fullname, '%'))")
    Page<Singer> findByFullnamePage(String fullname, Pageable pageable);
}