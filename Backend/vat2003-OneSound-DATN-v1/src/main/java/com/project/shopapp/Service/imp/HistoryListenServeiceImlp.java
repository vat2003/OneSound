package com.project.shopapp.Service.imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.HistoryListenServeice;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.HistoryListen;
import com.project.shopapp.entity.ListeningStats;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.HistoryListenDAO;
import com.project.shopapp.repository.SongDAO;

@Service
public class HistoryListenServeiceImlp implements HistoryListenServeice {

    @Autowired
    private SongDAO songDao;
    @Autowired
    private AccountDAO userDao;

    @Autowired
    private HistoryListenDAO listenDAO;

    @Override
    public void addHistory(Long songId, Long userId, Date playDate) {
        Song s = songDao.findById(songId).get();
        Account u = userDao.findById(userId).get();
        HistoryListen hl = listenDAO.findBySongIdAndUserId(songId, userId, playDate)
                .orElse(new HistoryListen(s, userId, playDate));

        listenDAO.save(hl);

    }

    @Override
    public List<HistoryListen> finfByUserId(Long userId) {
        return listenDAO.finfByUserId(userId);
    }

    @Override
    public List<HistoryListen> finfByListentime(Date date) {
        return listenDAO.finfByListentime(date);
    }

    @Override
    public void deleteAllHisByUserId(Long userId) {
        listenDAO.deleteAllByUserId(userId);
    }

    @Override
    public void deleteByUserIdAndSongId(Long id) {
        listenDAO.deleteByUserIdAndSongId(id);
    }

}
