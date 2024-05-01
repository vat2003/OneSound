package com.project.shopapp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.HistoryListen;
import com.project.shopapp.entity.ListeningStats;

import jakarta.transaction.Transactional;

/**
 * HistoryListenDAO
 */
public interface HistoryListenDAO extends JpaRepository<HistoryListen, Long> {
    @Query("SELECT ls FROM HistoryListen ls WHERE ls.song.id = :songId AND ls.userId = :userId AND ls.listenTime = :listenTime")
    Optional<HistoryListen> findBySongIdAndUserId(@Param("songId") Long songId, @Param("userId") Long userId,
                                                  @Param("listenTime") Date listenTime);

    @Query("SELECT ls FROM HistoryListen ls WHERE ls.userId = :userId order by ls.listenTime desc")
    List<HistoryListen> finfByUserId(@Param("userId") Long userId);

    @Query("SELECT ls FROM HistoryListen ls WHERE ls.listenTime = :listenTime")
    List<HistoryListen> finfByListentime(@Param("listenTime") Date date);

    // void deleteAllByUserId(Long userId);

    @Transactional
    void deleteAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM HistoryListen hl WHERE hl.id = :id")
    void deleteByUserIdAndSongId(@Param("id") Long id);

}