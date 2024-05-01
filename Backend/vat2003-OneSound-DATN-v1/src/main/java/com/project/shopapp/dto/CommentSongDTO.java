package com.project.shopapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.entity.Song;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSongDTO {

    @JsonProperty("userId")
    private Long user;

    @JsonProperty("songId")
    private Long song;

    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date likeDate = new Date();

    @JsonProperty("text")
    private String text;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("repCommentId")
    private Long repCommentId;
}
