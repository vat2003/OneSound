package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FavoriteSong;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Song;

public interface FavoriteSongService {
    List<FavoriteSong> getAllFavoriteSong();

    void addFavoriteSong(Account accountId, Song songId);

    void removeFavoriteSong(Long accountId, Long songId);

    FavoriteSong getFavoriteSongByUserAndSong(Long accountId, Long songId);

    List<FavoriteSong> getAllLikedSongsByUser(Long accountId);

}
