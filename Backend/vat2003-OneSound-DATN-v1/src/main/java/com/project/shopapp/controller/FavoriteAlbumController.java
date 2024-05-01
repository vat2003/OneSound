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

import com.project.shopapp.Service.FavoriteAlbumService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.FavoriteAlbum;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class FavoriteAlbumController {
    @Autowired
    FavoriteAlbumService favoriteAlbumService;

    public FavoriteAlbumController(FavoriteAlbumService favoriteAlbumService) {
        this.favoriteAlbumService = favoriteAlbumService;
    }

    @GetMapping("/favoriteAlbum")
    public List<FavoriteAlbum> getAllFavorite() {
        return favoriteAlbumService.getAllFavoriteAlbum();
    }

    @PostMapping("/favoriteAlbum")
    public ResponseEntity<?> addFavoriteAlbum(@RequestBody Map<String, Long> requestBody) {
        try {
            Long accountId = requestBody.get("accountId");
            Long albumId = requestBody.get("albumId");

            Account account = new Account();
            account.setId(accountId);

            Album album = new Album();
            album.setId(albumId);
            favoriteAlbumService.addFavoriteAlbum(account, album);

            return ResponseEntity.ok("Album added to FavoriteAlbum successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Album to the FavoriteAlbum.");
        }
    }

    @DeleteMapping("/favoriteAlbum")
    public ResponseEntity<?> removeFavoriteAlbum(@RequestBody Map<String, Long> requestBody) {
        try {
            Long accountId = requestBody.get("accountId");
            Long albumId = requestBody.get("albumId");

            favoriteAlbumService.removeFavoriteAlbum(accountId, albumId);
            return ResponseEntity.ok("Album removed from FavoriteAlbum successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove Album from FavoriteAlbum.");
        }
    }

    @GetMapping("/favoriteAlbum/isAlbumLiked/{accountId}/{albumId}")
    public ResponseEntity<?> isAlbumLikedByUser(@PathVariable Long accountId, @PathVariable Long albumId) {
        try {
//            Long accountId = requestBody.get("accountId");
//            Long albumId = requestBody.get("albumId");

            FavoriteAlbum favoriteAlbum = favoriteAlbumService.getFavoriteAlbumByUserAndAlbum(accountId, albumId);

            if (favoriteAlbum != null) {
                return ResponseEntity.ok(favoriteAlbum);
            } else {
                return ResponseEntity.ok(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking if the Album is liked by the user.");
        }
    }

    @GetMapping("/favoriteAlbum/{userId}")
    public ResponseEntity<List<FavoriteAlbum>> getFavoriteAlbumsByUserId(@PathVariable Long userId) {
        List<FavoriteAlbum> favoriteAlbums = favoriteAlbumService.getAllLikedAlbumsByUser(userId);

        if (favoriteAlbums.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(favoriteAlbums);
        }
    }
}
