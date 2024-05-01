package com.project.shopapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Playlist;

public interface PlaylistDAO extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findById(Long id);

    Optional<Playlist> findByName(String name);

    @Query("SELECT p FROM Playlist p WHERE p.user_id.id = :userId")
    List<Playlist> findByUserId(@Param("userId") Long userId);

    @Procedure(name = "DeletePlaylistAndSongsalong")
    void deletePlaylistAndSongsalong(@Param("playlistId") Long playlistId);
}
