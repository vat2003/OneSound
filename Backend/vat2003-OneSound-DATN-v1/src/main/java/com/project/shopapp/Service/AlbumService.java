package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;

public interface AlbumService {

    Page<Album> findAllAlbums(Pageable pageable);

    List<Album> getAll();

    List<Album> getAllInactive();

    Album getAlbumById(Long id);

    Album createAlbum(Album Album);

    Album updateAlbum(Long id, Album Album);

    Album inactiveAlbum(Long id);

    Album restoreAlbum(Long id);

    List<Album> findByTitleContaining(String title);

    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Album> searchByTitle(String title, Pageable pageable);

    List<Album> findAlbumByTitle(String title);
}
