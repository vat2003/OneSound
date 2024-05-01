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
import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.repository.AuthorDAO;

import java.io.File;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class AuthorController {

    @Autowired
    private AuthorDAO dao;

    @Autowired
    UploadService uploadService;

    public AuthorController(AuthorDAO singerService) {
        this.dao = singerService;
    }
    
    @GetMapping("/Author/Authors")
    public Page<Author> getAllAuthors(Pageable pageable) {
        return dao.findAll(pageable);
    }


    @GetMapping("/Author")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(dao.findByActive(true));
    }
    @GetMapping("/Author/In")
    public ResponseEntity<List<Author>> getAllAuthorsIn() {
        return ResponseEntity.ok(dao.findByActive(false));
    }

    
    @GetMapping("/Author/{id}")
    public ResponseEntity<Author> getSingerById(@PathVariable Long id) {
        Author auth = dao.findById(id).orElse(null);
        return ResponseEntity.ok(auth);
    }
    
    @GetMapping("/Author/name/{name}")
    public List<Author> getSingerByName(@PathVariable String name) {
        List<Author> auth = dao.findByFullnameIgnoreCase(name);
        return auth;
    }

    @GetMapping("/Author/getAuthorByName/{title}")
    public Page<Author> getAlbumByTitle(@PathVariable String title, Pageable pageable) {
        return dao.findByFullnamePage(title, pageable);
    }


    @PostMapping("/Author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        // Validate author data before saving
        Author savedAuthor = dao.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @PutMapping("/Author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        // Validate updated data before saving
        Author author = dao.save(updatedAuthor);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/Author/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        dao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
