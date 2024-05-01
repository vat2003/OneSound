package com.project.shopapp.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String description;
    private String image;
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<SingerAlbum> singerAlbums;

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<SongSinger> songSinger;

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<FavoriteSinger> favoriteSinger;

}
