package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.Service.GenreService;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.Singer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("album/getall")
    public Page<Album> getAlbum(Pageable pageable) {
        return albumService.findAllAlbums(pageable);
    }

    @GetMapping("album")
    public List<Album> getAll() {
        return albumService.getAll();
    }

    @GetMapping("album/getAllInactive")
    public List<Album> getAllInactive() {
        return albumService.getAllInactive();
    }

    @GetMapping("album/getbyid/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @GetMapping("album/getAlbumByTitle")
    public Page<Album> getAlbumByTitle(@RequestParam String title, Pageable pageable) {
        return albumService.searchByTitle(title, pageable);
    }

    @GetMapping("/album/name/{name}")
    public List<Album> getAlbumByName(@PathVariable String name) {
        return albumService.findByTitleContaining(name);
    }

    @GetMapping("/album/title/{name}")
    public List<Album> getAlbumByTitle(@PathVariable("name") String name) {
        return albumService.findAlbumByTitle(name);
    }

    @PostMapping("album/create")
    public Album createAlbum(@RequestBody Album album) {
        Album a = album;
        a.setActive(true);
        return albumService.createAlbum(a);

    }

    @PutMapping("album/update/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        return albumService.updateAlbum(id, album);
    }

    @PutMapping("album/inactive/{id}")
    public Album inactiveAlbum(@PathVariable Long id) {
        return albumService.inactiveAlbum(id);
    }

    @PutMapping("album/restore/{id}")
    public Album restoreAlbum(@PathVariable Long id) {
        return albumService.restoreAlbum(id);
    }

    // @DeleteMapping("album/delete/{id}")
    // public ResponseEntity<?> delAlbum(@PathVariable Long id) {
    // albumService.deleteAlbum(id);
    // Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
    // return ResponseEntity.ok(response);
    // }

}
