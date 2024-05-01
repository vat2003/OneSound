package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SingerAlbum;

public interface SingerAlbumService {

    List<SingerAlbum> getAllSingerAlbums();

    SingerAlbum addSingerAlbum(SingerAlbumId singerAlbumId);

    void removeSingerAlbum(Long singerAlbumId);

    void deleteByAlbumId(Long albumId);
}
