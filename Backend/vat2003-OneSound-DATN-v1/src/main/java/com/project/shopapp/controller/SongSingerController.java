package com.project.shopapp.controller;

import java.util.List;

import com.project.shopapp.entity.*;
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

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.SongSinger;
import com.project.shopapp.repository.SingerDAO;
import com.project.shopapp.repository.SongSingerDAO;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SongSingerController {
	@Autowired
	SingerDAO sdao;

	@Autowired
	SongSingerDAO dao;

	private final com.project.shopapp.Service.SongSingerService SongSingerService;

	@Autowired
	public SongSingerController(com.project.shopapp.Service.SongSingerService SingerAlbumService) {
		this.SongSingerService = SingerAlbumService;
	}

	@GetMapping("SongSinger/getall")
	public List<SongSinger> getAlbum() {
		return SongSingerService.getAllSongSingers();
	}

	// @PostMapping("/SongSinger/create")
	// public SongSinger createAlbum(@RequestBody SongSingerId SongSingerId) {
	// return SongSingerService.addSongSinger(SongSingerId);

	// }

//    @PostMapping("SongSinger/create")
//    public SongSinger createAlbum(@RequestBody SongSingerId SongSingerId) {
//        return SongSingerService.addSongSinger(SongSingerId);
//    }

	@PostMapping("SongSinger/create")
	public SongSinger createAlbum(@RequestBody SongSingerId songSingerId) {
		System.out.println("Singer: ------------------->" + songSingerId.getSingerId());
		System.out.println("Song: ------------------->" + songSingerId.getSongId());
		return SongSingerService.createSongSinger(songSingerId);
	}

	//    @DeleteMapping("SongSinger/delete-by-singer/{id}")
//    public void deleteBySinger(@PathVariable("id") Long SongSingerId) {
//    	List<SongSinger> ss=SongSingerService.findById(SongSingerId);
//        SongSingerService.deleteAll(ss);
//    }
//

	@GetMapping("SongSinger/get-by-song/{id}")
	public List<SongSinger> getBySongSinger(@PathVariable("id") Long SongSingerId) {
		List<SongSinger> ss = dao.findBySongId(SongSingerId);
		return ss;
	}

	@GetMapping("SongSinger/get-by-singer/{id}")
	public List<SongSinger> getBySongSingerID(@PathVariable("id") Long SongSingerId) {
		List<SongSinger> ss = dao.findBySingerId(SongSingerId);
		return ss;
	}

//	@DeleteMapping("SongSinger/delete-by-song/{id}")
//	public void deleteBySong(@PathVariable("id") Long songId) {
//		try {
////			List<SongSinger> list = dao.findBySongId(songId);
////			System.out.println("+++++++++++++ID NHẠC+++++++" + list);
////			dao.deleteAll(list);
////			dao.removeSongSinger(songId);
////			System.out.println(a);
//		} catch (Exception e) {
//			System.err.println("BUGGGG"+e);
////		}
//
//		}
//	}
	@DeleteMapping("SongSinger/delete-by-song/{id}")
	public void deleteBySong(@PathVariable("id") Long SongSingerId) {
		SongSingerService.deleteBySongId(SongSingerId);
	}

// Xóa singer album theo album id
//    @DeleteMapping("SongSinger/deleteByAlbumId/{id}")
//    public void deleteByAlbumId(@PsathVariable("id") Long AlbumId) {
//        SongSingerService.removeSongSinger(AlbumId);
//    }


}

