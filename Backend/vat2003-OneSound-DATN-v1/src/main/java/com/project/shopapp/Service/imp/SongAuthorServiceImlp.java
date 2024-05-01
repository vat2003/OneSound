package com.project.shopapp.Service.imp;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongAuthorService;
import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongAuthorDAO;
import com.project.shopapp.repository.AuthorDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongAuthorServiceImlp implements SongAuthorService {

    private final SongAuthorDAO SongAuthorDao;


    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    SongDAO songDAO;

    @Autowired
    public SongAuthorServiceImlp(SongAuthorDAO SongAuthorDao) {
        this.SongAuthorDao = SongAuthorDao;
    }

    @Override
    public List<SongAuthor> getAllSongAuthors() {
        // TODO Auto-generated method stub
        return SongAuthorDao.findAll();
    }

    @Override
    @Transactional
    public void deleteBySongId(Long songId) {
        SongAuthorDao.deleteBySongId(songId);
    }

    @Override
    public void removeSongAuthor(Long SongId) {

        // // Tìm kiếm Song
        // Song a = adao.findById(SongAuthorId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongAuthor> SongAuthor = SongAuthorDao.findBySongId(SongId);
        SongAuthorDao.deleteAll(SongAuthor);

    }

//    @Override
//    public void deleteBySongId(Long SongId) {
//        SongAuthorDao.deleteBySongId(SongId);
//    }

    @Override
    public SongAuthor createAuthor(SongAuthorId songAuthorId) {
        SongAuthor sa = new SongAuthor();

        Song s = songDAO.findById(songAuthorId.getSongId()).get();
        Author a = authorDAO.findById(songAuthorId.getAuthorId()).get();

        sa.setAuthor(a);
        sa.setSong(s);
        sa.setId(songAuthorId);
        return SongAuthorDao.save(sa);

    }

}
