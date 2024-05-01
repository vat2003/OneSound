package com.project.shopapp.controller;

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
import com.project.shopapp.Service.CommentYoutubeService;
import com.project.shopapp.dto.CommentDTO;
import com.project.shopapp.dto.CommentYoutubeDTO;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class ComemtYoutubeController {
    @Autowired
    CommentYoutubeService CommentYoutubeService;

    public ComemtYoutubeController(CommentYoutubeService CommentYoutubeService) {
        this.CommentYoutubeService = CommentYoutubeService;
    }

    @GetMapping("/Comemtyt")
    public List<CommentYoutube> findAllComments() {
        return CommentYoutubeService.findAll();
    }

    @GetMapping("/Comemtyt/{songId}/with-replies")
    public ResponseEntity<List<CommentYoutubeDTO>> getCommentsWithReplies(@PathVariable String songId) {
        List<CommentYoutubeDTO> commentsWithReplies = CommentYoutubeService.getCommentsWithReplies(songId);
        return ResponseEntity.ok(commentsWithReplies);
    }

    @GetMapping("/Comemtyt/{youtubeid}")
    public List<CommentYoutube> findCommentsBySongId(@PathVariable String youtubeid) {
        return CommentYoutubeService.findByYoutubeId(youtubeid);
    }

    @GetMapping("/Comemtyt/{songId}/top")
    public ResponseEntity<List<CommentYoutube>> findTopLevelComments(@PathVariable String songId) {
        List<CommentYoutube> comments = CommentYoutubeService.findBySongIdAndRepCommentIdIsNull(songId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/Comemtyt/{youtube_id}/{commentId}/replies")
    public ResponseEntity<List<CommentYoutube>> findRepliesToComment(@PathVariable("youtube_id") String youtube_id,
            @PathVariable("commentId") Long commentId) {
        List<CommentYoutube> replies = CommentYoutubeService.findBySongIdAndRepCommentId(youtube_id, commentId);
        return ResponseEntity.ok().body(replies);
    }

    @PostMapping("/Comemtyt")
    public ResponseEntity<CommentYoutube> addComment(@RequestBody CommentYoutube comment,
            @RequestParam("youtube_id") String songId,
            @RequestParam("userId") Long userId) {
        try {
            CommentYoutube addedComment = CommentYoutubeService.addComment(comment, songId,
                    userId);
            return ResponseEntity.ok(addedComment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/Comemtyt/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
            @RequestParam("youtube_id") String songId,
            @RequestParam("userId") Long userId,
            @RequestBody CommentYoutube comment) {
        try {

            CommentYoutube existingComment = CommentYoutubeService.findById(commentId);
            if (existingComment == null) {
                return ResponseEntity.notFound().build();
            }

            if (!existingComment.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

            }
            if (!existingComment.getYoutube().getId().equals(songId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

            }

            existingComment.setText(comment.getText());
            existingComment.setActive(comment.isActive());
            CommentYoutube updated = CommentYoutubeService.updateComment(existingComment);

            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/Comemtyt/admin/{commentId}")
    public ResponseEntity<?> deleteCommentadmin(@PathVariable Long commentId) {
        try {
            CommentYoutubeService.DeleteRelatedComments(commentId);
            Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("Comemtyt/byidandcommentid/{id}/{userId}")
    public ResponseEntity<?> DeleteCommentIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        boolean isCommentBelongsToUser = CommentYoutubeService.isCommentBelongsToUser(id, userId);
        if (!isCommentBelongsToUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This comment does not belong to the user.");
        }
        CommentYoutubeService.DeleteRelatedComments(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
