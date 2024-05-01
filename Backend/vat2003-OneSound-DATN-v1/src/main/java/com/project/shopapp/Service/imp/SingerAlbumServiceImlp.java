package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SingerAlbumService;
import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SingerAlbum;
import com.project.shopapp.repository.AlbumDAO;
import com.project.shopapp.repository.SingerAlbumDAO;
import com.project.shopapp.repository.SingerDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SingerAlbumServiceImlp implements SingerAlbumService {

    private final SingerAlbumDAO singerAlbumDao;

    @Autowired
    SingerDAO sdao;

    @Autowired
    AlbumDAO adao;

    @Autowired
    public SingerAlbumServiceImlp(SingerAlbumDAO singerAlbumDao) {
        this.singerAlbumDao = singerAlbumDao;
    }

    @Override
    public List<SingerAlbum> getAllSingerAlbums() {
        // TODO Auto-generated method stub
        return singerAlbumDao.findAll();
    }

    @Override
    public SingerAlbum addSingerAlbum(SingerAlbumId singerAlbumId) {

        SingerAlbum singerAlbum = new SingerAlbum();

        // Tìm kiếm Singer
        Singer s = sdao.findById(singerAlbumId.getSingerId())
                .orElseThrow(() -> new EntityNotFoundException("Singer not found"));

        // Tìm kiếm Album
        Album a = adao.findById(singerAlbumId.getAlbumId())
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));

        singerAlbum.setAlbum(a);
        singerAlbum.setSinger(s);
        singerAlbum.setId(singerAlbumId);

        return singerAlbumDao.save(singerAlbum);
    }

    @Override
    public void removeSingerAlbum(Long albumId) {

        // // Tìm kiếm Album
        // Album a = adao.findById(singerAlbumId)
        // .orElseThrow(() -> new EntityNotFoundException("Album not found"));
        List<SingerAlbum> singerAlbum = singerAlbumDao.findAllByAlbumId(albumId);
        singerAlbumDao.deleteAll(singerAlbum);

    }

    @Override
    public void deleteByAlbumId(Long albumId) {
        singerAlbumDao.deleteByAlbumId(albumId);
    }

}
