package com.project.shopapp.dto;

import java.util.Date;
import java.util.List;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Youtube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentYoutubeDTO {
    private Long id;
    private String text;
    private boolean active;
    private List<CommentYoutubeDTO> replies;
    private Account user;
    private Youtube youtube;
    private Date likeDate;

}