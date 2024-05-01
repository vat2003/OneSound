package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

public interface CommentYoutubeDao extends JpaRepository<CommentYoutube, Long> {
    List<CommentYoutube> findByYoutube_Id(String youtubeId);

    List<CommentYoutube> findByYoutubeIdAndRepCommentIdIsNull(String youtubeId);

    List<CommentYoutube> findByYoutubeIdAndRepCommentId(String youtubeId, Long commentId);

    @Procedure(name = "DeleteRelatedCommentsYoutube")
    void DeleteRelatedCommentsYoutube(@Param("commentId") Long playlistId);

}
