package com.project.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.Service.SingerService;
import com.project.shopapp.Service.UploadService;
import com.project.shopapp.Service.imp.AccountServiceImlp;
import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SingerFullInfoDTO;
import com.project.shopapp.repository.SingerDAO;
import com.project.shopapp.utils.thongbao;

import java.io.File;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private SingerDAO SingerDAO;

    @Autowired
    UploadService uploadService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping("/Singer")
    public List<Singer> getAllSingers() {
        return singerService.getAllSingers();
    }

    @GetMapping("/Singer/get-singer-by-name")
    public Singer gétingerByName(@RequestParam String name) {
        return singerService.getSingersByName(name);
    }

    @GetMapping("/Singer/Singers")
    public Page<Singer> getAllSingers(Pageable pageable) {
        return singerService.getAllSingers(pageable);
    }

    @GetMapping("/Singer/{id}")
    public ResponseEntity<Singer> getSingerById(@PathVariable Long id) {
        Singer employee = singerService.getSingerById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/Singer/profile/{id}")
    public ResponseEntity<?> getSingerById1(@PathVariable Long id) {
        SingerFullInfoDTO singerFullInfoDTO = singerService.getSingerFullInfoById(id);

        if (singerFullInfoDTO != null) {
            return ResponseEntity.ok(singerFullInfoDTO);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/Singer/getSingerByName/{title}")
    public Page<Singer> getAlbumByTitle(@PathVariable String title, Pageable pageable) {
        return SingerDAO.findByFullnamePage(title, pageable);
    }

    @GetMapping("/Singer/getAllByAlbumId/{id}")
    public List<Singer> getAllSingerByAlbumId(@PathVariable Long id) {
        List<Singer> singers = singerService.findAllSingerByAlbumId(id);
        return singers;
    }

    @PostMapping("/Singer")
    public Singer createEmployee(@RequestBody Singer Singer) {
        System.out.println(Singer);
        return singerService.createSinger(Singer);
    }

    @PutMapping("/Singer/{id}")
    public Singer updateEmployee(@PathVariable long id, @RequestBody Singer Singer) {
        return singerService.updateSinger(id, Singer);
    }

    @DeleteMapping("/Singer/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        Singer employeeToDelete = singerService.getSingerById(id);
        singerService.deleteSinger(employeeToDelete.getId());
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Singer/getAllSingerActive")
    public List<Singer> getAllSingerByAlbumId1() {
        List<Singer> singers = singerService.findAllSingerActive();
        return singers;
    }

}
