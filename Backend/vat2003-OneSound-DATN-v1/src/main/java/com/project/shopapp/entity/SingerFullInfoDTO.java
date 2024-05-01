package com.project.shopapp.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerFullInfoDTO {
    private Long id;
    private String fullname;
    private String description;
    private String image;
    private List<SingerAlbum> singerAlbums;
    private List<SongSinger> songSinger;

    private List<FavoriteSinger> favoriteSinger;

}
