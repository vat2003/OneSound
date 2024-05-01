package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.Songservice;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.SongDAO;

@Service
public class SongServiceIml implements Songservice {

    @Autowired
    SongDAO dao;

    @Override
    public List<Song> getAllSong() {
        return dao.findAll();
    }

}
