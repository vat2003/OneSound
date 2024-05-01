package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.shopapp.Service.FavoriteSongService;
import com.project.shopapp.Service.PlaylistSongService;
import com.project.shopapp.composite.FavoriteSongId;
import com.project.shopapp.composite.PlaylistSongId;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FavoriteSong;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.FavoriteSongDAO;

@Service
public class FavoriteSongServiceImlp implements FavoriteSongService {

    @Autowired
    FavoriteSongDAO dao;

    @Override
    public List<FavoriteSong> getAllFavoriteSong() {
        return dao.findAll();
    }

    @Override
    public void addFavoriteSong(Account accountId, Song songId) {
        FavoriteSongId FavoriteSongId = new FavoriteSongId(accountId.getId(), songId.getId());

        FavoriteSong FavoriteSong = new FavoriteSong();
        FavoriteSong.setId(FavoriteSongId);

        FavoriteSong.setSong(songId);
        FavoriteSong.setUser(accountId);
        dao.save(FavoriteSong);
    }

    @Override
    public void removeFavoriteSong(Long accountId, Long songId) {
        FavoriteSongId FavoriteSongId = new FavoriteSongId(accountId, songId);
        dao.deleteById(FavoriteSongId);
    }

    @Override
    public FavoriteSong getFavoriteSongByUserAndSong(Long accountId, Long songId) {
        FavoriteSongId favoriteSongId = new FavoriteSongId(accountId, songId);
        return dao.findById(favoriteSongId).orElse(null);
    }

    @Override
    public List<FavoriteSong> getAllLikedSongsByUser(Long accountId) {
        return dao.findByUserId(accountId);
    }

}
