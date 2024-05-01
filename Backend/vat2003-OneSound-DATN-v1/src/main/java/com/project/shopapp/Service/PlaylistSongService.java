package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Role;
import com.project.shopapp.entity.Song;

public interface PlaylistSongService {
    List<PlaylistSong> getAllPlaylistSong();

    PlaylistSong createPlaylistSong(PlaylistSong playlistSong);

    void addSongToPlaylist(Playlist playlistId, Song songId);

    void removeSongFromPlaylist(Long playlistId, Long songId);

    PlaylistSong findSongFromPlaylist(Long playlistId, Long songId);

    void removeAllSongsFromPlaylist(Long playlistId);

    List<PlaylistSong> findSongsByPlaylistId(Long playlistId);

    // void xoaketao(Long playlistId);

}
