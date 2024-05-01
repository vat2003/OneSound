package com.project.shopapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.composite.PlaylistSongId;
import com.project.shopapp.entity.PlaylistSong;

public interface PlaylistSongDAO extends JpaRepository<PlaylistSong, PlaylistSongId> {
    @Modifying
    @Query("DELETE FROM PlaylistSong ps WHERE ps.id.playlistId = :playlistId")
    void deleteAllSongsByPlaylistId(@Param("playlistId") Long playlistId);

    List<PlaylistSong> findByPlaylistId(Long playlistId);

}
