package com.project.shopapp.dto;

import java.util.Date;
import java.util.List;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private boolean active;
    private List<CommentDTO> replies;
    private Account user;
    private Song song;
    private Date likeDate;
}
