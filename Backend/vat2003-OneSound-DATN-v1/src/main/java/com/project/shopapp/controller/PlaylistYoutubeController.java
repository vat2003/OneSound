package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.Service.PlaylistSongService;
import com.project.shopapp.Service.PlaylistYoutubeService;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.PlaylistYoutube;
import com.project.shopapp.entity.Youtube;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class PlaylistYoutubeController {
    @Autowired
    private PlaylistYoutubeService PlaylistYoutubeService;

    public PlaylistYoutubeController(PlaylistYoutubeService PlaylistYoutubeService) {
        this.PlaylistYoutubeService = PlaylistYoutubeService;
    }

    @GetMapping("/PlaylistYoutube")
    public List<PlaylistYoutube> getAllPlaylistYoutubes() {
        return PlaylistYoutubeService.getAllPlaylistYoutube();
    }

    @GetMapping("/PlaylistYoutube/{playlistId}/{youtubeId}")
    public ResponseEntity<?> findYoutubeInPlaylist(@PathVariable Long playlistId,
            @PathVariable String youtubeId) {
        try {
            PlaylistYoutube PlaylistYoutube = PlaylistYoutubeService.findYoutubeFromPlaylist(playlistId, youtubeId);

            if (PlaylistYoutube != null) {
                return ResponseEntity.ok(PlaylistYoutube);
            } else {

                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/PlaylistYoutube/playlist/{playlistId}")
    public ResponseEntity<?> getAllSongsInPlaylist(@PathVariable Long playlistId) {
        try {
            List<PlaylistYoutube> songsInPlaylist = PlaylistYoutubeService.findSongsByPlaylistId(playlistId);

            if (!songsInPlaylist.isEmpty()) {
                return ResponseEntity.ok(songsInPlaylist);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No songs found in the playlist.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get songs in the playlist.");
        }
    }

    @PostMapping("/PlaylistYoutube")
    public ResponseEntity<?> addYoutubeToPlaylist1(@RequestBody Map<String, Object> requestBody) {
        try {
            Long playlistId = Long.parseLong(requestBody.get("playlistId").toString());
            String youtubeId = (String) requestBody.get("youtubeId");

            Playlist playlist = new Playlist();
            playlist.setId(playlistId);

            Youtube youtube = new Youtube();
            youtube.setId(youtubeId);

            PlaylistYoutubeService.addYoutubeToPlaylist(playlist, youtube);

            return ResponseEntity.ok("Song added to playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add song to the playlist.");
        }
    }

    @DeleteMapping("/PlaylistYoutube/{playlistId}/{youtubeId}")
    public ResponseEntity<?> removeYoutubeFromPlaylist(@PathVariable Long playlistId, @PathVariable String youtubeId) {
        try {
            PlaylistYoutubeService.removeYoutubeFromPlaylist(playlistId, youtubeId);
            return ResponseEntity.ok("Song removed from playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove song from playlist.");
        }
    }

    @DeleteMapping("/PlaylistYoutube/{playlistId}")
    public ResponseEntity<?> removeAllYoutubesFromPlaylist(@PathVariable Long playlistId) {
        try {
            PlaylistYoutubeService.removeAllYoutubesFromPlaylist(playlistId);

            return ResponseEntity.ok("All songs removed from playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove songs from playlist.");
        }
    }
}
