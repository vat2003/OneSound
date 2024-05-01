package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.PlaylistYoutube;
import com.project.shopapp.entity.Youtube;

public interface PlaylistYoutubeService {
    List<PlaylistYoutube> getAllPlaylistYoutube();

    PlaylistYoutube createPlaylistYoutube(PlaylistYoutube playlistYoutube);

    void addYoutubeToPlaylist(Playlist playlistId, Youtube youtubeId);

    void removeYoutubeFromPlaylist(Long playlistId, String youtubeId);

    PlaylistYoutube findYoutubeFromPlaylist(Long playlistId, String youtubeId);

    void removeAllYoutubesFromPlaylist(Long playlistId);

    List<PlaylistYoutube> findSongsByPlaylistId(Long playlistId);

}
