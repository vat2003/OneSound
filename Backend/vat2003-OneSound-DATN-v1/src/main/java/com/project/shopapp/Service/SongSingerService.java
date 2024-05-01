package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongSinger;

public interface SongSingerService {

    List<SongSinger> getAllSongSingers();
    
    SongSinger createSongSinger(SongSingerId songSingerId);

    void removeSongSinger(Long SongSingerId);

    void deleteBySongId(Long albumId);
}
