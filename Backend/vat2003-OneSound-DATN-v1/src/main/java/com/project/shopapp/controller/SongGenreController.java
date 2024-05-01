package com.project.shopapp.controller;

import java.util.List;

import com.project.shopapp.Service.SongAuthorService;
import com.project.shopapp.Service.SongGenreService;
import com.project.shopapp.Service.SongSingerService;
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

import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.repository.GenreDAO;
import com.project.shopapp.repository.SongGenreDAO;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SongGenreController {
	@Autowired
	GenreDAO sdao;

	@Autowired
	SongGenreDAO dao;


	private final com.project.shopapp.Service.SongGenreService SongGenreService;

	@Autowired
	public SongGenreController(com.project.shopapp.Service.SongGenreService SingerAlbumService) {
		this.SongGenreService = SingerAlbumService;
	}

	@GetMapping("SongGenre/getall")
	public List<SongGenre> getAlbum() {
		return SongGenreService.getAllSongGenres();
	}

	// @PostMapping("/SongGenre/create")
	// public SongGenre createAlbum(@RequestBody SongGenreId SongGenreId) {
	// return SongGenreService.addSongGenre(SongGenreId);

	// }

//    @PostMapping("SongGenre/create")
//    public SongGenre createAlbum(@RequestBody SongGenreId SongGenreId) {
//        return SongGenreService.addSongGenre(SongGenreId);
//    }

	@PostMapping("SongGenre/create")
	public SongGenre createAlbum(@RequestBody SongGenreId songGenreId) {
		System.out.println("Genre: ------------------->" + songGenreId.getGenreId());
		System.out.println("Song: ------------------->" + songGenreId.getSongId());
		return SongGenreService.createSongGenre(songGenreId);
	}

//    @DeleteMapping("SongGenre/delete-by-Genre/{id}")
//    public void deleteByGenre(@PathVariable("id") Long SongGenreId) {
//    	List<SongGenre> ss=SongGenreService.findById(SongGenreId);
//        SongGenreService.deleteAll(ss);
//    }
//    
	@GetMapping("SongGenre/get-by-song/{id}")
	public List<SongGenre> getBySongGenre(@PathVariable("id") Long SongGenreId) {
		List<SongGenre> ss = dao.findBySongId(SongGenreId);
		return ss;
	}
	
	@GetMapping("SongGenre/get-by-genre/{id}")
	public List<SongGenre> getByGenreSong(@PathVariable("id") Long SongGenreId) {
		List<SongGenre> ss = dao.findByGenreId(SongGenreId);
		return ss;
	}

	@DeleteMapping("SongGenre/delete-by-song/{id}")
	public void deleteBySong(@PathVariable("id") Long SongSingerId) {
		SongGenreService.deleteBySongId(SongSingerId);
	}

	// Xóa Genre album theo album id
//    @DeleteMapping("SongGenre/deleteByAlbumId/{id}")
//    public void deleteByAlbumId(@PsathVariable("id") Long AlbumId) {
//        SongGenreService.removeSongGenre(AlbumId);
//    }

}
