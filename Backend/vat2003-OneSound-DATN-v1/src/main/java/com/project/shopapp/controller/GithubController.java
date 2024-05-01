package com.project.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.EmailService;
import com.project.shopapp.Service.GithubService;
import com.project.shopapp.entity.Email;
import com.project.shopapp.entity.Github;
import com.project.shopapp.security.DataNotFoundException;
import com.project.shopapp.utils.CheckSocialAccountResponse;

@RestController
@RequestMapping("${api.prefix}/githubs")
@CrossOrigin(origins = "*")
public class GithubController {
    @Autowired
    GithubService GithubService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUesrs(
            @PathVariable("id") Long id) throws DataNotFoundException {
        Github emailResponse = this.GithubService.getUserById(id);
        return ResponseEntity.ok(emailResponse);
    }
}
