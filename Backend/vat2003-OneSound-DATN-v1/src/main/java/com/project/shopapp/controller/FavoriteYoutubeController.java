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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.FavoriteYoutubeService;

import com.project.shopapp.entity.Account;

import com.project.shopapp.entity.FavoriteYoutube;

import com.project.shopapp.entity.Youtube;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class FavoriteYoutubeController {
    @Autowired
    FavoriteYoutubeService FavoriteYoutubeService;

    public FavoriteYoutubeController(FavoriteYoutubeService FavoriteYoutubeService) {
        this.FavoriteYoutubeService = FavoriteYoutubeService;
    }

    @GetMapping("/favoriteYoutube")
    public List<FavoriteYoutube> getAllFavoriteYoutube() {
        return FavoriteYoutubeService.getAllFavoriteYoutube();
    }

    @PostMapping("/favoriteYoutube")
    public ResponseEntity<?> addFavoriteYoutube(@RequestBody Map<String, Object> requestBody) {
        try {
            Long accountId = Long.parseLong(requestBody.get("accountId").toString());
            String youtubeId = (String) requestBody.get("youtubeId");

            Account account = new Account();
            account.setId(accountId);

            Youtube youtube = new Youtube();
            youtube.setId(youtubeId);
            FavoriteYoutubeService.addFavoriteYoutube(account, youtube);

            return ResponseEntity.ok("Youtube added to FavoriteYoutube successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Youtube to the FavoriteYoutube.");
        }
    }

    @DeleteMapping("/favoriteYoutube")
    public ResponseEntity<?> removeFavoriteYoutube(@RequestBody Map<String, Object> requestBody) {
        try {
            Long accountId = Long.parseLong(requestBody.get("accountId").toString());
            String youtubeId = (String) requestBody.get("youtubeId");

            FavoriteYoutubeService.removeFavoriteYoutube(accountId, youtubeId);
            return ResponseEntity.ok("youtube removed from FavoriteYoutube successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove youtube from FavoriteYoutube.");
        }
    }

    @GetMapping("/favoriteYoutube/isYoutubeLiked")
    public ResponseEntity<?> isYoutubeLikedByUser(@RequestBody Map<String, Object> requestBody) {
        try {
            Long accountId = Long.parseLong(requestBody.get("accountId").toString());
            String youtubeId = (String) requestBody.get("youtubeId");

            FavoriteYoutube favoriteYoutube = FavoriteYoutubeService.getFavoriteYoutubeByUserAndYoutube(accountId,
                    youtubeId);

            if (favoriteYoutube != null) {
                return ResponseEntity.ok(favoriteYoutube);
            } else {
                return ResponseEntity.ok(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking if the Youtube is liked by the user.");
        }
    }

    @GetMapping("/favoriteYoutube/{userId}")
    public ResponseEntity<List<FavoriteYoutube>> getFavoriteYoutubesByUserId(@PathVariable Long userId) {
        List<FavoriteYoutube> favoriteYoutube = FavoriteYoutubeService.getAllLikedYoutubesByUser(userId);

        if (favoriteYoutube.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(favoriteYoutube);
        }
    }
}
