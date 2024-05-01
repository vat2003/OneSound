package com.project.shopapp.Service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.HistoryListen;

public interface HistoryListenServeice {

    void addHistory(Long songId, Long userId, Date playDate);

    List<HistoryListen> finfByUserId(@Param("userId") Long userId);

    List<HistoryListen> finfByListentime(@Param("listenTime") Date date);

    void deleteAllHisByUserId(Long userId);

    void deleteByUserIdAndSongId(Long id);
}
