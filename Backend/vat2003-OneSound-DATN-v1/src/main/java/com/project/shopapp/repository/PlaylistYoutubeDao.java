package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.composite.PlaylistYoutubeId;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.PlaylistYoutube;

public interface PlaylistYoutubeDao extends JpaRepository<PlaylistYoutube, PlaylistYoutubeId> {
    @Modifying
    @Query("DELETE FROM PlaylistYoutube py WHERE py.id.playlistId = :playlistId")
    void deleteAllYoutubesByPlaylistId(@Param("playlistId") Long playlistId);

    @Query("SELECT py FROM PlaylistYoutube py WHERE py.id.playlistId = :playlistId")
    List<PlaylistYoutube> findByPlaylistYoutubeId(@Param("playlistId") Long playlistId);

}
