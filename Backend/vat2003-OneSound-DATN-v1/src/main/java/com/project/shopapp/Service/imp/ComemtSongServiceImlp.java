
package com.project.shopapp.Service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.dto.CommentDTO;
import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.ComemtSongDao;
import com.project.shopapp.repository.SongDAO;

@Service
public class ComemtSongServiceImlp implements ComemtSongService {

    @Autowired
    private ComemtSongDao comemtSongDao;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private SongDAO SongDAO;

    @Override
    public List<CommentSong> findAll() {
        return comemtSongDao.findAll();

    }

    @Override
    public List<CommentSong> findBySongId(Long songId) {
        return comemtSongDao.findBySongId(songId);
    }

    @Override
    public List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId) {
        return comemtSongDao.findBySongIdAndRepCommentId(songId, commentId);
    }

    @Override
    public List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId) {
        return comemtSongDao.findBySongIdAndRepCommentIdIsNull(songId);
    }

    @Override
    public CommentSong addComment(CommentSong comment, Long songId, Long accountId) {

        Account user = AccountDAO.findById(accountId).get();

        Song song = SongDAO.findById(songId).get();

        comment.setSong(song);
        comment.setUser(user);

        return comemtSongDao.save(comment);
    }

    @Override
    public CommentSong findById(Long commentId) {
        Optional<CommentSong> commentOptional = comemtSongDao.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public CommentSong updateComment(CommentSong comment) {
        return comemtSongDao.save(comment);
    }

    @Override
    public void DeleteRelatedComments(Long commentId) {
        comemtSongDao.DeleteRelatedComments11(commentId);
    }

    @Override
    public boolean isCommentBelongsToUser(Long commentId, Long userId) {
        CommentSong comment = comemtSongDao.findById(commentId).orElse(null);
        if (comment != null && comment.getUser().getId().equals(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public Account findAccountByCommentId(Long commentId) {
        return comemtSongDao.findAccountByCommentId(commentId);
    }

    @Override
    public List<CommentDTO> getCommentsWithReplies(Long songId) {
        List<CommentSong> topLevelComments = comemtSongDao.findBySongIdAndRepCommentIdIsNull(songId);
        List<CommentDTO> commentsWithReplies = new ArrayList<>();

        for (CommentSong topLevelComment : topLevelComments) {
            CommentDTO topLevelDTO = convertToDTO(topLevelComment);

            List<CommentDTO> replyDTOs = new ArrayList<>();

            // Lấy danh sách các bình luận nhỏ của bình luận cấp độ 1
            List<CommentSong> level2Comments = comemtSongDao.findBySongIdAndRepCommentId(songId,
                    topLevelComment.getId());
            for (CommentSong level2Comment : level2Comments) {
                CommentDTO level2DTO = convertToDTO(level2Comment);
                List<CommentSong> level3Comments = comemtSongDao.findBySongIdAndRepCommentId(songId,
                        level2Comment.getId());
                List<CommentDTO> level3DTOs = new ArrayList<>();
                for (CommentSong level3Comment : level3Comments) {
                    level3DTOs.add(convertToDTO(level3Comment));
                }
                level2DTO.setReplies(level3DTOs);
                replyDTOs.add(level2DTO);
            }

            topLevelDTO.setReplies(replyDTOs);
            commentsWithReplies.add(topLevelDTO);
        }

        // Sắp xếp mảng commentsWithReplies tăng dần theo thuộc tính likeDate
        Collections.sort(commentsWithReplies, new Comparator<CommentDTO>() {
            @Override
            public int compare(CommentDTO c1, CommentDTO c2) {
                return c2.getLikeDate().compareTo(c1.getLikeDate());
            }
        });

        return commentsWithReplies;
    }

    private CommentDTO convertToDTO(CommentSong comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setUser(comment.getUser());
        dto.setSong(comment.getSong());
        dto.setText(comment.getText());
        dto.setLikeDate(comment.getLikeDate());
        dto.setActive(comment.isActive());
        return dto;
    }

}