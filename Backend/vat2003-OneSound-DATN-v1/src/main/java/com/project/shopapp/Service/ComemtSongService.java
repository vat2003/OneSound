
package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.CommentDTO;
import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;

/**
 * ComemtSongService
 */
public interface ComemtSongService {

    Account findAccountByCommentId(Long commentId);

    List<CommentSong> findAll();

    CommentSong findById(Long commentId);

    CommentSong updateComment(CommentSong comment);

    List<CommentSong> findBySongId(Long songId);

    List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId);

    CommentSong addComment(CommentSong comment, Long songId, Long accountId);

    void DeleteRelatedComments(Long commentId);

    boolean isCommentBelongsToUser(Long commentId, Long userId);

    List<CommentDTO> getCommentsWithReplies(Long songId);

}