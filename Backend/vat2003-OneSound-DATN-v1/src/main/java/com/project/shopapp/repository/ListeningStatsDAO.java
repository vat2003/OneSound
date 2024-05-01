package com.project.shopapp.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.ListeningStats;

public interface ListeningStatsDAO extends JpaRepository<ListeningStats, Long> {

    // Optional<ListeningStats> findBySongIdAndDateLis(Long songId, Date dateLis);

    @Query("SELECT ls FROM ListeningStats ls WHERE ls.song.id = :songId AND ls.dateLis = :dateLis")
    Optional<ListeningStats> findBySongIdAndDateLis(@Param("songId") Long songId, @Param("dateLis") Date dateLis);

    @Query("SELECT p FROM ListeningStats p WHERE p.dateLis = :playDate ORDER BY p.listens DESC")
    List<ListeningStats> findTopByPlayDateOrderByCountDesc(LocalDate playDate);

    @Query("SELECT ls FROM ListeningStats ls")
    List<ListeningStats> getAllListens();

    @Query("SELECT ls FROM ListeningStats ls WHERE ls.dateLis BETWEEN :formDate AND :toDate ORDER BY  ls.dateLis DESC ")
    List<ListeningStats> getAllListensBetweenDateLis(@Param("formDate") Date formDate, @Param("toDate") Date toDate);

    @Query("SELECT ls FROM ListeningStats ls ORDER BY  ls.listens DESC ")
    List<ListeningStats> getTop10Listens();
    // select ls.*, s.name from listening_stats ls join songs s on ls.song_id = s.id

}
