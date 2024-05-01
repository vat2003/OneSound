package com.project.shopapp.Service.imp;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SongSinger;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongSingerDAO;
import com.project.shopapp.repository.SingerDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongSingerServiceImlp implements SongSingerService {

    private final SongSingerDAO SongSingerDao;

    @Autowired
    SingerDAO singerdao;

    @Autowired
    SongDAO songdao;

    @Autowired
    public SongSingerServiceImlp(SongSingerDAO SongSingerDao) {
        this.SongSingerDao = SongSingerDao;
    }

    @Override
    public List<SongSinger> getAllSongSingers() {
        // TODO Auto-generated method stub
        return SongSingerDao.findAll();
    }

   

    @Override
//    @Transactional
    public void removeSongSinger(Long SongId) {

        // // Tìm kiếm Song
        // Song a = adao.findById(SongSingerId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongSinger> SongSinger = SongSingerDao.findBySongId(SongId);
        SongSingerDao.deleteAll(SongSinger);

    }



//    @Override
//    public void deleteBySongId(Long SongId) {
//        SongSingerDao.deleteBySongId(SongId);
//    }

    @Override
    @Transactional
    public void deleteBySongId(Long songId) {
        SongSingerDao.deleteBySongId(songId);
    }

	@Override
	public SongSinger createSongSinger(SongSingerId songSingerId) {
		SongSinger ss = new SongSinger();
		
		Song s = songdao.findById(songSingerId.getSongId()).get();
		Singer singer = singerdao.findById(songSingerId.getSingerId()).get();
		
		ss.setId(songSingerId);
		ss.setSinger(singer);
		ss.setSong(s);
		return SongSingerDao.save(ss);
	}

}
