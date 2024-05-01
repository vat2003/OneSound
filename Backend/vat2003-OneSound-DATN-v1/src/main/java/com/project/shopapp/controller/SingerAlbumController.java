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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.Service.SingerAlbumService;
import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SingerAlbum;
import com.project.shopapp.entity.SongSinger;
import com.project.shopapp.repository.SingerAlbumDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SingerAlbumController {

    private final SingerAlbumService SingerAlbumService;
    
    @Autowired
    SingerAlbumDAO dao;

    @Autowired
    public SingerAlbumController(SingerAlbumService SingerAlbumService) {
        this.SingerAlbumService = SingerAlbumService;
    }

    @GetMapping("singerAlbum/getall")
    public List<SingerAlbum> getAlbum() {
        return SingerAlbumService.getAllSingerAlbums();
    }
    
	@GetMapping("singerAlbum/get-by-singer/{id}")
	public List<SingerAlbum> getByAlbumSingerID(@PathVariable("id") Long SongSingerId) {
		List<SingerAlbum> ss = dao.findBySingerId(SongSingerId);
		return ss;
	}
	

	@GetMapping("singerAlbum/get-by-album/{id}")
	public List<SingerAlbum> getByAlbumID(@PathVariable("id") Long SongSingerId) {
		List<SingerAlbum> ss = dao.findByAlbumId(SongSingerId);
		return ss;
	}

    // @PostMapping("/singerAlbum/create")
    // public SingerAlbum createAlbum(@RequestBody SingerAlbumId singerAlbumId) {
    // return SingerAlbumService.addSingerAlbum(singerAlbumId);

    // }
    @PostMapping("singerAlbum/create")
    public SingerAlbum createAlbum(@RequestBody SingerAlbumId singerAlbumId) {
        return SingerAlbumService.addSingerAlbum(singerAlbumId);
    }

    @DeleteMapping("singerAlbum/delete/{id}")
    public void deleteAlbum(@PathVariable("id") Long singerAlbumId) {
        SingerAlbumService.removeSingerAlbum(singerAlbumId);
    }

    
    // Xóa singer album theo album id
    @DeleteMapping("singerAlbum/deleteByAlbumId/{id}")
    public void deleteByAlbumId(@PathVariable("id") Long AlbumId) {
        SingerAlbumService.removeSingerAlbum(AlbumId);
    }

}
