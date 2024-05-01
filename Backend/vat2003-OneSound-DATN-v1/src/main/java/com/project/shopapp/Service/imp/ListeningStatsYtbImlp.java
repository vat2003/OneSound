package com.project.shopapp.Service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ListeningStatsServevic;
import com.project.shopapp.Service.ListeningStatsYtbServevic;
import com.project.shopapp.entity.ListeningStats;
import com.project.shopapp.entity.ListeningStatsYtb;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.ListeningStatsDAO;
import com.project.shopapp.repository.ListeningStatsYtbDAO;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.YoutubeDAO;

@Service
public class ListeningStatsYtbImlp implements ListeningStatsYtbServevic {
    @Autowired
    private ListeningStatsYtbDAO playCountRepository;
    @Autowired
    private YoutubeDAO ytbDao;

    @Override
    public void incrementPlayCount(String ytbId, Date playDate) {
        Youtube y = ytbDao.findById(ytbId).get();
        ListeningStatsYtb playCount = playCountRepository.findByYtbIdAndDateLis(ytbId, playDate)
                .orElse(new ListeningStatsYtb(0L, playDate, y));
        playCount.setListens(playCount.getListens() + 1);
        playCountRepository.save(playCount);
    }

}
