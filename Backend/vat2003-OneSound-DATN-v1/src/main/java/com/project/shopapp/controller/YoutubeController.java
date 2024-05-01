package com.project.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.RoleService;
import com.project.shopapp.entity.Role;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.YoutubeDAO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class YoutubeController {

    @Autowired
    YoutubeDAO dao;

    @GetMapping("/youtube")
    public List<Youtube> FindallYoutube() {
        List<Youtube> savedYoutube = dao.findAll();
        return savedYoutube;
    }

    @PostMapping("/youtube")
    public ResponseEntity<Youtube> createYoutube(@RequestBody Youtube youtube) {
        Youtube savedYoutube = dao.save(youtube);
        return new ResponseEntity<>(savedYoutube, HttpStatus.CREATED);
    }

}
