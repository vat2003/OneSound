package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongAuthor;

public interface SongAuthorService {

    List<SongAuthor> getAllSongAuthors();

    void removeSongAuthor(Long SongAuthorId);

    void deleteBySongId(Long albumId);

    SongAuthor createAuthor(SongAuthorId songAuthorId);
}
