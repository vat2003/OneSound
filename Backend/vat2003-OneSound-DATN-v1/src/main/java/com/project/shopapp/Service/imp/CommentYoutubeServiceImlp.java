package com.project.shopapp.Service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.CommentYoutubeService;
import com.project.shopapp.dto.CommentYoutubeDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentYoutube;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.CommentYoutubeDao;
import com.project.shopapp.repository.YoutubeDAO;

@Service
public class CommentYoutubeServiceImlp implements CommentYoutubeService {

    @Autowired
    CommentYoutubeDao dao;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private YoutubeDAO YoutubeDAO;

    @Override
    public List<CommentYoutube> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CommentYoutube> findByYoutubeId(String YoutubeId) {
        return dao.findByYoutube_Id(YoutubeId);
    }

    @Override
    public List<CommentYoutube> findBySongIdAndRepCommentIdIsNull(String youtube_id) {
        return dao.findByYoutubeIdAndRepCommentIdIsNull(youtube_id);

    }

    @Override
    public List<CommentYoutube> findBySongIdAndRepCommentId(String youtube_id, Long commentId) {

        return dao.findByYoutubeIdAndRepCommentId(youtube_id, commentId);
    }

    @Override
    public CommentYoutube addComment(CommentYoutube comment, String songId, Long accountId) {

        Account user = AccountDAO.findById(accountId).get();

        Youtube song = YoutubeDAO.findById(songId).get();

        comment.setYoutube(song);
        comment.setUser(user);

        return dao.save(comment);
    }

    @Override
    public CommentYoutube findById(Long commentId) {
        Optional<CommentYoutube> commentOptional = dao.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public CommentYoutube updateComment(CommentYoutube comment) {
        return dao.save(comment);
    }

    @Override
    public void DeleteRelatedComments(Long commentId) {
        dao.DeleteRelatedCommentsYoutube(commentId);
    }

    @Override
    public boolean isCommentBelongsToUser(Long commentId, Long userId) {
        CommentYoutube comment = dao.findById(commentId).orElse(null);
        if (comment != null && comment.getUser().getId().equals(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public List<CommentYoutubeDTO> getCommentsWithReplies(String songId) {
        List<CommentYoutube> topLevelComments = dao.findByYoutubeIdAndRepCommentIdIsNull(songId);
        List<CommentYoutubeDTO> commentsWithReplies = new ArrayList<>();

        for (CommentYoutube topLevelComment : topLevelComments) {
            CommentYoutubeDTO topLevelDTO = convertToDTO(topLevelComment);

            List<CommentYoutubeDTO> replyDTOs = new ArrayList<>();

            // Lấy danh sách các bình luận nhỏ của bình luận cấp độ 1
            List<CommentYoutube> level2Comments = dao.findByYoutubeIdAndRepCommentId(songId,
                    topLevelComment.getId());
            for (CommentYoutube level2Comment : level2Comments) {
                CommentYoutubeDTO level2DTO = convertToDTO(level2Comment);
                List<CommentYoutube> level3Comments = dao.findByYoutubeIdAndRepCommentId(songId,
                        level2Comment.getId());
                List<CommentYoutubeDTO> level3DTOs = new ArrayList<>();
                for (CommentYoutube level3Comment : level3Comments) {
                    level3DTOs.add(convertToDTO(level3Comment));
                }
                level2DTO.setReplies(level3DTOs);
                replyDTOs.add(level2DTO);
            }

            topLevelDTO.setReplies(replyDTOs);
            commentsWithReplies.add(topLevelDTO);
        }

        // Sắp xếp mảng commentsWithReplies tăng dần theo thuộc tính likeDate
        Collections.sort(commentsWithReplies, new Comparator<CommentYoutubeDTO>() {
            @Override
            public int compare(CommentYoutubeDTO c1, CommentYoutubeDTO c2) {
                return c2.getLikeDate().compareTo(c1.getLikeDate());
            }
        });

        return commentsWithReplies;
    }

    private CommentYoutubeDTO convertToDTO(CommentYoutube comment) {
        CommentYoutubeDTO dto = new CommentYoutubeDTO();
        dto.setId(comment.getId());
        dto.setUser(comment.getUser());
        dto.setYoutube(comment.getYoutube());
        dto.setText(comment.getText());
        dto.setLikeDate(comment.getLikeDate());
        dto.setActive(comment.isActive());
        return dto;
    }
}
