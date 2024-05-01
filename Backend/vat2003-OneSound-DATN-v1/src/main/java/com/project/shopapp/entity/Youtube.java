package com.project.shopapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Youtubes")
public class Youtube {

    @Id
    private String id;
    private String title;
    private String description;
    private String thumbnails;
    private String channelTitle;
    private String publishTime;

    @JsonIgnore
    @OneToMany(mappedBy = "youtube")
    private List<ListeningStatsYtb> listeningStatsYtb;

    @JsonIgnore
    @OneToMany(mappedBy = "youtube", cascade = CascadeType.ALL)
    private List<CommentYoutube> comments;

}
