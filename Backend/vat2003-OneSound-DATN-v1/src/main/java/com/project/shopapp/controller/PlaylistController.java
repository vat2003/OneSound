package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.Service.RoleService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.Role;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class PlaylistController {

    @Autowired
    private PlaylistService PlaylistService;

    public PlaylistController(PlaylistService PlaylistService) {
        this.PlaylistService = PlaylistService;
    }

    @GetMapping("/Playlist")
    public List<Playlist> getAllSingers() {
        return PlaylistService.getAllPlaylist();
    }

    @GetMapping("/Playlist/{name}")
    public Optional<Playlist> getAllSingers1(@PathVariable String name) {
        return PlaylistService.findByName(name);
    }

    @GetMapping("/Playlist/id/{id}")
    public Optional<Playlist> getAllSingers3(@PathVariable Long id) {
        return PlaylistService.findById(id);
    }

    @GetMapping("/Playlist/user/{userId}")
    public ResponseEntity<List<Playlist>> getPlaylistsByUserId(@PathVariable Long userId) {
        List<Playlist> playlists = PlaylistService.findByUser_id(userId);
        return ResponseEntity.ok(playlists);
    }

    @PostMapping("/Playlist")
    public ResponseEntity<?> createPlaylist(@RequestBody Map<String, Object> playlistData) {
        try {
            String name = (String) playlistData.get("name");
            Long userId = ((Number) ((Map<?, ?>) playlistData.get("user_id")).get("id")).longValue();
            Playlist playlist = new Playlist();
            playlist.setName(name);
            Playlist createdPlaylist = PlaylistService.createPlaylist(playlist, userId);
            return ResponseEntity.ok(createdPlaylist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/Playlist/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        try {
            Playlist updatedPlaylist = PlaylistService.updatePlaylist(id, playlist);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("Playlist/{id}")
    public ResponseEntity<?> delAlbum(@PathVariable Long id) {
        PlaylistService.deletePlaylist(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
