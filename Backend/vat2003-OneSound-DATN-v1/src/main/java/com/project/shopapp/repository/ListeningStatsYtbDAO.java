package com.project.shopapp.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.ListeningStats;
import com.project.shopapp.entity.ListeningStatsYtb;

public interface ListeningStatsYtbDAO extends JpaRepository<ListeningStatsYtb, Long> {
    @Query("SELECT ls FROM ListeningStatsYtb ls WHERE ls.youtube.id = :ytbId AND ls.dateLis = :dateLis")
    Optional<ListeningStatsYtb> findByYtbIdAndDateLis(@Param("ytbId") String ytbId, @Param("dateLis") Date dateLis);

    @Query("SELECT p FROM ListeningStatsYtb p WHERE p.dateLis = :playDate ORDER BY p.listens DESC")
    List<ListeningStatsYtb> findTopByPlayDateOrderByCountDesc(LocalDate playDate);
}
