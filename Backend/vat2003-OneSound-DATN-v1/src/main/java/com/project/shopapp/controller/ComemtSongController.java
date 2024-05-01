
package com.project.shopapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.dto.CommentDTO;
import com.project.shopapp.dto.CommentReplyDTO;
import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.repository.AuthorDAO;
import com.project.shopapp.repository.ComemtSongDao;

/**
 * ComemtSongController
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class ComemtSongController {

    @Autowired
    ComemtSongService ComemtSongService;

    public ComemtSongController(ComemtSongService ComemtSongService) {
        this.ComemtSongService = ComemtSongService;
    }

    @GetMapping("/comments")
    public List<CommentSong> findAllComments() {
        return ComemtSongService.findAll();
    }

    @GetMapping("/comments/{songId}")
    public List<CommentSong> findCommentsBySongId(@PathVariable Long songId) {
        return ComemtSongService.findBySongId(songId);
    }

    @GetMapping("/comments/{songId}/with-replies")
    public ResponseEntity<List<CommentDTO>> getCommentsWithReplies(@PathVariable Long songId) {
        List<CommentDTO> commentsWithReplies = ComemtSongService.getCommentsWithReplies(songId);
        return ResponseEntity.ok(commentsWithReplies);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentSong> addComment(@RequestBody CommentSong comment, @RequestParam("songId") Long songId,
            @RequestParam("userId") Long userId) {
        try {
            CommentSong addedComment = ComemtSongService.addComment(comment, songId,
                    userId);
            return ResponseEntity.ok(addedComment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }

    }

    @GetMapping("/comments/{songId}/{commentId}/replies")
    public ResponseEntity<List<CommentSong>> findRepliesToComment(@PathVariable("songId") Long songId,
            @PathVariable("commentId") Long commentId) {
        List<CommentSong> replies = ComemtSongService.findBySongIdAndRepCommentId(songId, commentId);
        return ResponseEntity.ok().body(replies);
    }

    @GetMapping("/comments/User/{Userid}")
    public ResponseEntity<Account> findRepliesToComment1(@PathVariable("Userid") Long Userid) {

        return ResponseEntity.ok().body(ComemtSongService.findAccountByCommentId(Userid));
    }

    @GetMapping("/comments/{songId}/top")
    public ResponseEntity<List<CommentSong>> findTopLevelComments(@PathVariable Long songId) {
        List<CommentSong> comments = ComemtSongService.findBySongIdAndRepCommentIdIsNull(songId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
            @RequestParam("songId") Long songId,
            @RequestParam("userId") Long userId,
            @RequestBody CommentSong comment) {
        try {

            CommentSong existingComment = ComemtSongService.findById(commentId);
            if (existingComment == null) {
                return ResponseEntity.notFound().build();
            }

            if (!existingComment.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

            }
            if (!existingComment.getSong().getId().equals(songId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

            }

            existingComment.setText(comment.getText());
            existingComment.setActive(comment.isActive());
            CommentSong updated = ComemtSongService.updateComment(existingComment);

            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/comments/admin/{commentId}")
    public ResponseEntity<?> deleteCommentadmin(@PathVariable Long commentId) {
        try {
            ComemtSongService.DeleteRelatedComments(commentId);
            Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("comments/byidandcommentid/{id}/{userId}")
    public ResponseEntity<?> DeleteCommentIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        boolean isCommentBelongsToUser = ComemtSongService.isCommentBelongsToUser(id, userId);
        if (!isCommentBelongsToUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This comment does not belong to the user.");
        }
        ComemtSongService.DeleteRelatedComments(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}