package com.project.shopapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FavoriteYoutube;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.entity.Youtube;

public interface FavoriteYoutubeService {
    List<FavoriteYoutube> getAllFavoriteYoutube();

    void addFavoriteYoutube(Account accountId, Youtube youtubeId);

    void removeFavoriteYoutube(Long accountId, String youtubeId);

    FavoriteYoutube getFavoriteYoutubeByUserAndYoutube(Long accountId, String youtubeId);

    List<FavoriteYoutube> getAllLikedYoutubesByUser(Long accountId);
}
