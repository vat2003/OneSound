package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.CommentDTO;
import com.project.shopapp.dto.CommentYoutubeDTO;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

public interface CommentYoutubeService {
    List<CommentYoutube> findAll();

    CommentYoutube findById(Long commentId);

    CommentYoutube updateComment(CommentYoutube comment);

    List<CommentYoutube> findByYoutubeId(String YoutubeId);

    List<CommentYoutube> findBySongIdAndRepCommentIdIsNull(String youtube_id);

    List<CommentYoutube> findBySongIdAndRepCommentId(String youtube_id, Long commentId);

    CommentYoutube addComment(CommentYoutube comment, String songId, Long accountId);

    void DeleteRelatedComments(Long commentId);

    boolean isCommentBelongsToUser(Long commentId, Long userId);

    List<CommentYoutubeDTO> getCommentsWithReplies(String songId);
}
