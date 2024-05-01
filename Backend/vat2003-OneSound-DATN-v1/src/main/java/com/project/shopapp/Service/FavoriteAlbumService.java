package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.FavoriteAlbum;

public interface FavoriteAlbumService {

    List<FavoriteAlbum> getAllFavoriteAlbum();

    void addFavoriteAlbum(Account accountId, Album albumId);

    void removeFavoriteAlbum(Long accountId, Long albumId);

    FavoriteAlbum getFavoriteAlbumByUserAndAlbum(Long accountId, Long albumId);

    List<FavoriteAlbum> getAllLikedAlbumsByUser(Long accountId);
}
