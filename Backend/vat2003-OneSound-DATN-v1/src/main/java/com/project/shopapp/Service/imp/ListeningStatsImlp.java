package com.project.shopapp.Service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ListeningStatsServevic;
import com.project.shopapp.entity.ListeningStats;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.ListeningStatsDAO;
import com.project.shopapp.repository.SongDAO;

@Service
public class ListeningStatsImlp implements ListeningStatsServevic {
    @Autowired
    private ListeningStatsDAO playCountRepository;
    @Autowired
    private SongDAO songDao;

    @Override
    public void incrementPlayCount(Long songId, Date playDate) {
        Song s = songDao.findById(songId).get();
        ListeningStats playCount = playCountRepository.findBySongIdAndDateLis(songId, playDate)
                .orElse(new ListeningStats(0L, playDate, s));
        playCount.setListens(playCount.getListens() + 1);
        playCountRepository.save(playCount);
    }

}
