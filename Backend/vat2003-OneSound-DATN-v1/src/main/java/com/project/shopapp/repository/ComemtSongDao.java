
package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;

import jakarta.transaction.Transactional;

public interface ComemtSongDao extends JpaRepository<CommentSong, Long> {
    List<CommentSong> findBySongId(Long songId);

    List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId);

    @Query("SELECT cs.user FROM CommentSong cs WHERE cs.id = :commentId")
    Account findAccountByCommentId(@Param("commentId") Long commentId);

    @Procedure(name = "DeleteRelatedComments11")
    void DeleteRelatedComments11(@Param("commentId") Long playlistId);

}