package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.FavoriteAlbumService;
import com.project.shopapp.composite.FavoriteAlbumId;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.FavoriteAlbum;
import com.project.shopapp.repository.FavoriteAlbumDAO;

@Service
public class FavoriteAlbumServiceImlp implements FavoriteAlbumService {
    @Autowired
    FavoriteAlbumDAO dao;

    @Override
    public List<FavoriteAlbum> getAllFavoriteAlbum() {
        return dao.findAll();
    }

    @Override
    public void addFavoriteAlbum(Account accountId, Album albumId) {
        FavoriteAlbumId FavoriteSongId = new FavoriteAlbumId(accountId.getId(), albumId.getId());

        FavoriteAlbum FavoriteAlbum = new FavoriteAlbum();
        FavoriteAlbum.setId(FavoriteSongId);

        FavoriteAlbum.setAlbum(albumId);
        FavoriteAlbum.setUser(accountId);
        dao.save(FavoriteAlbum);
    }

    @Override
    public void removeFavoriteAlbum(Long accountId, Long albumId) {
        FavoriteAlbumId FavoriteAlbumId = new FavoriteAlbumId(accountId, albumId);
        dao.deleteById(FavoriteAlbumId);
    }

    @Override
    public FavoriteAlbum getFavoriteAlbumByUserAndAlbum(Long accountId, Long albumId) {
        FavoriteAlbumId FavoriteAlbumId = new FavoriteAlbumId(accountId, albumId);
        return dao.findByUserIdAndAlbumId(accountId, albumId);
//        return dao.findById(FavoriteAlbumId).orElse(null);
    }

    @Override
    public List<FavoriteAlbum> getAllLikedAlbumsByUser(Long accountId) {
        return dao.findByUserId(accountId);
    }

}
