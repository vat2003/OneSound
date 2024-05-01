package com.project.shopapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;

public interface PlaylistService {
    List<Playlist> getAllPlaylist();

    Playlist createPlaylist(Playlist playlist, Long userId);

    Playlist updatePlaylist(Long id, Playlist playlist);

    void deletePlaylist(Long id);

    Optional<Playlist> findByName(String name);

    Optional<Playlist> findById(Long id);

    List<Playlist> findByUser_id(Long userId);

}
