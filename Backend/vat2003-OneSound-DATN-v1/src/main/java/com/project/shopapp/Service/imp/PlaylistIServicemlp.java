package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.PlaylistDAO;

@Service
public class PlaylistIServicemlp implements PlaylistService {

    @Autowired
    PlaylistDAO dao;

    @Autowired
    AccountDAO AccountDAO;

    @Override
    public List<Playlist> getAllPlaylist() {
        return dao.findAll();
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, Long userId) {
        Optional<Account> userOptional = AccountDAO.findById(userId);
        Account user = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        playlist.setUser_id(user);

        return dao.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(Long id, Playlist playlist) {
        Optional<Playlist> existingPlaylistOptional = dao.findById(id);
        Playlist existingPlaylist = existingPlaylistOptional.orElseThrow();
        existingPlaylist.setName(playlist.getName());
        return dao.save(existingPlaylist);
    }

    @Override
    public void deletePlaylist(Long id) {
        // dao.deleteById(id);
        dao.deletePlaylistAndSongsalong(id);
    }

    @Override
    public Optional<Playlist> findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Playlist> findByUser_id(Long userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return dao.findById(id);
    }

}
