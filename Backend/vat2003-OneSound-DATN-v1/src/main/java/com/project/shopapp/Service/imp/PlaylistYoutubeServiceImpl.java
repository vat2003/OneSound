package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.PlaylistYoutubeService;
import com.project.shopapp.composite.PlaylistSongId;
import com.project.shopapp.composite.PlaylistYoutubeId;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.PlaylistYoutube;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.PlaylistYoutubeDao;

@Service
public class PlaylistYoutubeServiceImpl implements PlaylistYoutubeService {

    @Autowired
    PlaylistYoutubeDao dao;

    @Override
    public List<PlaylistYoutube> getAllPlaylistYoutube() {
        return dao.findAll();
    }

    @Override
    public PlaylistYoutube createPlaylistYoutube(PlaylistYoutube playlistYoutube) {
        return dao.save(playlistYoutube);
    }

    @Override
    public void addYoutubeToPlaylist(Playlist playlistId, Youtube youtubeId) {
        PlaylistYoutubeId PlaylistYoutubeId = new PlaylistYoutubeId(playlistId.getId(), youtubeId.getId());
        PlaylistYoutube PlaylistYoutube = new PlaylistYoutube();
        PlaylistYoutube.setId(PlaylistYoutubeId);

        PlaylistYoutube.setYoutube(youtubeId);
        PlaylistYoutube.setPlaylist(playlistId);

        dao.save(PlaylistYoutube);
    }

    @Override
    public void removeYoutubeFromPlaylist(Long playlistId, String youtubeId) {
        PlaylistYoutubeId PlaylistYoutubeId = new PlaylistYoutubeId(playlistId, youtubeId);
        dao.deleteById(PlaylistYoutubeId);
    }

    @Override
    public PlaylistYoutube findYoutubeFromPlaylist(Long playlistId, String youtubeId) {
        Optional<PlaylistYoutube> optionalPlaylistYoutube = dao.findById(new PlaylistYoutubeId(playlistId, youtubeId));
        return optionalPlaylistYoutube.orElse(null);
    }

    @Override
    public void removeAllYoutubesFromPlaylist(Long playlistId) {
        dao.deleteAllYoutubesByPlaylistId(playlistId);
    }

    @Override
    public List<PlaylistYoutube> findSongsByPlaylistId(Long playlistId) {
        return dao.findByPlaylistYoutubeId(playlistId);
    }

}
