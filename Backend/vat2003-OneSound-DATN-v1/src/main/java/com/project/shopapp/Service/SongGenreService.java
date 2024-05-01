package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongGenre;

public interface SongGenreService {

    List<SongGenre> getAllSongGenres();
    
    SongGenre createSongGenre(SongGenreId songGenreId);

    void removeSongGenre(Long SongGenreId);

    void deleteBySongId(Long albumId);
}
