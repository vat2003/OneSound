package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.PlaylistSongService;
import com.project.shopapp.composite.PlaylistSongId;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.PlaylistSongDAO;

import jakarta.transaction.Transactional;

@Service
public class PlaylistSongServiceImlp implements PlaylistSongService {

    @Autowired
    PlaylistSongDAO dao;

    @Override
    public List<PlaylistSong> getAllPlaylistSong() {
        return dao.findAll();

    }

    @Override
    public PlaylistSong createPlaylistSong(PlaylistSong playlistSong) {
        return dao.save(playlistSong);
    }

    @Override
    public void addSongToPlaylist(Playlist playlist, Song song) {
        PlaylistSongId playlistSongId = new PlaylistSongId(playlist.getId(), song.getId());
        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setId(playlistSongId);

        playlistSong.setSong(song);
        playlistSong.setPlaylist(playlist);

        dao.save(playlistSong);
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        PlaylistSongId playlistSongId = new PlaylistSongId(playlistId, songId);
        dao.deleteById(playlistSongId);
    }

    @Override
    public PlaylistSong findSongFromPlaylist(Long playlistId, Long songId) {
        Optional<PlaylistSong> optionalPlaylistSong = dao.findById(new PlaylistSongId(playlistId, songId));
        return optionalPlaylistSong.orElse(null);
    }

    @Override
    @Transactional
    public void removeAllSongsFromPlaylist(Long playlistId) {
        dao.deleteAllSongsByPlaylistId(playlistId);
    }

    @Override
    public List<PlaylistSong> findSongsByPlaylistId(Long playlistId) {
        return dao.findByPlaylistId(playlistId);
    }

}
