package com.project.shopapp.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.project.shopapp.Service.ListeningStatsServevic;
import com.project.shopapp.Service.ListeningStatsYtbServevic;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.AlbumDAO;
import com.project.shopapp.repository.ListeningStatsDAO;
import com.project.shopapp.repository.SongDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class SongController {

    @Autowired
    SongDAO songDAO;
    @Autowired
    AlbumDAO albumDAO;

    @Autowired
    ListeningStatsServevic lisDao;

    @Autowired
    ListeningStatsYtbServevic lisYtbDao;

    @GetMapping("Song/getall")
    public Page<Song> getSong(Pageable pageable) {
        return songDAO.findAll(pageable);
    }

    @GetMapping("Song/activeFalse")
    public List<Song>getAllActiveFalse(){
        return songDAO.findByActiveFalse();
    }
    @GetMapping("Song/activeTrue")
    public List<Song>getAllActiveTrue(){
        return songDAO.findByActiveTrue();
    }

    @GetMapping("Song/findAll")
    public List<Song> getSongNonePage() {
        return songDAO.findAll();
    }

    @GetMapping("Song")
    public List<Song> getAll() {
        return songDAO.findAll();
    }

    @GetMapping("Song/getbyid/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songDAO.findById(id).get();
    }

    @GetMapping("Song/getSongByName")
    public Page<Song> getSongByName(@RequestParam String Name, Pageable pageable) {
        return songDAO.findByNameIgnoreCase(Name, pageable);
    }

    @GetMapping("Song/albumId/{albumId}")
    public ResponseEntity<List<Song>> getSongByAlbumId(@PathVariable Long albumId) {
        List<Song> song = songDAO.findSongsByAlbumId(albumId);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/Song/Name/{name}")
    public List<Song> getSongByName1(@PathVariable("name") String name) {
        return songDAO.findByName(name);
    }

    @PostMapping("Song/create")
    public ResponseEntity<Song> createAuthor(@RequestBody Song songRequest) {
        // // Validate author data before saving
        Song song = new Song();
        // Album al = new Album();
        song.setName(songRequest.getName());
        song.setImage(songRequest.getImage());
        song.setPath(songRequest.getPath());
        song.setRelease_date(songRequest.getRelease_date());
        // song.setLyrics(songRequest.getLyrics());
        song.setAlbum(songRequest.getAlbum());
        // // Cài đặt logic khác nếu cần

        songDAO.save(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(song);
    }

    @PutMapping("Song/update/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song Song) {
        return songDAO.save(Song);
    }

    @DeleteMapping("Song/delete/{id}")
    public ResponseEntity<?> delSong(@PathVariable Long id) {
        songDAO.deleteById(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("song/{songId}/play")
    public ResponseEntity<?> playSong(@PathVariable Long songId) {
        LocalDate todayLocalDate = LocalDate.now();
        // Chuyển đổi từ LocalDate sang Date
        Date todayDate = Date.from(todayLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        lisDao.incrementPlayCount(songId, todayDate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("song/ytb/{songId}/play")
    public ResponseEntity<?> increaseListenYtb(@PathVariable String ytbId) {
        LocalDate todayLocalDate = LocalDate.now();
        // Chuyển đổi từ LocalDate sang Date
        Date todayDate = Date.from(todayLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        lisYtbDao.incrementPlayCount(ytbId, todayDate);
        return ResponseEntity.ok().build();
    }

}