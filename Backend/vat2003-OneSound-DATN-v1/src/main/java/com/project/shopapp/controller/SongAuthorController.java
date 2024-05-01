package com.project.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.repository.SongAuthorDAO;
import com.project.shopapp.repository.SongAuthorDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SongAuthorController {

    @Autowired
    SongAuthorDAO SongAuthorService;

    private final com.project.shopapp.Service.SongAuthorService saService;

    @Autowired
    public SongAuthorController(com.project.shopapp.Service.SongAuthorService saService) {
        this.saService = saService;
    }


    @GetMapping("SongAuthor/getall")
    public List<SongAuthor> getAlbum() {
        return SongAuthorService.findAll();
    }

    // @PostMapping("/SongAuthor/create")
    // public SongAuthor createAlbum(@RequestBody SongAuthorId SongAuthorId) {
    // return SongAuthorService.addSongAuthor(SongAuthorId);

    // }
    @PostMapping("SongAuthor/create")
    public SongAuthor createSongAuthor(@RequestBody SongAuthorId SongAuthorId) {
        System.out.println("author: ------------------->" + SongAuthorId.getAuthorId());
        System.out.println("Song: ------------------->" + SongAuthorId.getSongId());
        return saService.createAuthor(SongAuthorId);
    }

    @PutMapping("SongAuthor/update")
    public SongAuthor updateSongAuthor(@RequestBody SongAuthor SongAuthorId) {
        return SongAuthorService.save(SongAuthorId);
    }

    @DeleteMapping("SongAuthor/delete-by-author/{id}")
    public void deleteByAuthor(@PathVariable("id") Long SongAuthorId) {
        List<SongAuthor> ss = SongAuthorService.findByAuthorId(SongAuthorId);
        SongAuthorService.deleteAll(ss);
    }

    @DeleteMapping("SongAuthor/delete-by-song/{id}")
    public void deleteBySong(@PathVariable("id") Long SongAuthorId) {
        saService.deleteBySongId(SongAuthorId);
    }
//    @DeleteMapping("SongAuthor/delete-by-song/{id}")
//    public void deleteBySong(@PathVariable("id") Long SongAuthorId) {
//        SongAuthorService.deleteBySongId(SongAuthorId);
//    }

    @GetMapping("SongAuthor/get-by-song/{id}")
    public List<SongAuthor> getBySongAuthor(@PathVariable("id") Long SongAuthorId) {
        List<SongAuthor> ss = SongAuthorService.findBySongId(SongAuthorId);
        return ss;
    }

    // Xóa Author album theo album id
//    @DeleteMapping("SongAuthor/deleteByAlbumId/{id}")
//    public void deleteByAlbumId(@PsathVariable("id") Long AlbumId) {
//        SongAuthorService.removeSongAuthor(AlbumId);
//    }

}
