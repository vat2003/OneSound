package com.project.shopapp.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.SongSinger;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SongSingerDAO extends JpaRepository<SongSinger, SongSingerId> {

    List<SongSinger> findBySongId(Long songid);

    List<SongSinger> findBySingerId(Long singer);
//	void deleteBySongId(long id);

    void deleteBySongId(Long albumId);
}
