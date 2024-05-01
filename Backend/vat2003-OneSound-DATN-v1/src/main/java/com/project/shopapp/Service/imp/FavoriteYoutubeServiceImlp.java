package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.FavoriteYoutubeService;
import com.project.shopapp.composite.FavoriteSongId;
import com.project.shopapp.composite.FavoriteYoutubeId;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FavoriteYoutube;
import com.project.shopapp.entity.FavoriteYoutube;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.FavoriteYoutubeDAO;

@Service
public class FavoriteYoutubeServiceImlp implements FavoriteYoutubeService {

    @Autowired
    FavoriteYoutubeDAO dao;

    @Override
    public List<FavoriteYoutube> getAllFavoriteYoutube() {
        return dao.findAll();
    }

    @Override
    public void addFavoriteYoutube(Account accountId, Youtube youtubeId) {
        FavoriteYoutubeId FavoriteYoutubeId = new FavoriteYoutubeId(accountId.getId(), youtubeId.getId());

        FavoriteYoutube FavoriteYoutube = new FavoriteYoutube();
        FavoriteYoutube.setId(FavoriteYoutubeId);

        FavoriteYoutube.setYoutube(youtubeId);
        FavoriteYoutube.setUser(accountId);
        dao.save(FavoriteYoutube);
    }

    @Override
    public void removeFavoriteYoutube(Long accountId, String youtubeId) {
        FavoriteYoutubeId FavoriteYoutubeId = new FavoriteYoutubeId(accountId, youtubeId);
        dao.deleteById(FavoriteYoutubeId);
    }

    @Override
    public FavoriteYoutube getFavoriteYoutubeByUserAndYoutube(Long accountId, String youtubeId) {
        FavoriteYoutubeId FavoriteYoutubeId = new FavoriteYoutubeId(accountId, youtubeId);
        return dao.findById(FavoriteYoutubeId).orElse(null);
    }

    @Override
    public List<FavoriteYoutube> getAllLikedYoutubesByUser(Long accountId) {
        return dao.findByUserId(accountId);
    }

}
