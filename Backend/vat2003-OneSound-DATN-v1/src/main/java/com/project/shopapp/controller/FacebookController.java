package com.project.shopapp.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.FacebookService;
import com.project.shopapp.entity.Facebook;
import com.project.shopapp.security.DataNotFoundException;
import com.project.shopapp.utils.CheckSocialAccountResponse;

@RestController
@RequestMapping("${api.prefix}/facebooks")
@CrossOrigin(origins = "*")
public class FacebookController {
    @Autowired
    private FacebookService facebookService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUesrs(
            @PathVariable("id") Long id) throws DataNotFoundException {
        Facebook facebook = this.facebookService.getUserById(id);
        return ResponseEntity.ok(facebook);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RequestParam("email") String email) throws DataNotFoundException {
        if (this.facebookService.getFacebookByEmail(email).getId() == 0) {
            return ResponseEntity.ok(
                    CheckSocialAccountResponse.builder()
                            .message("failed")
                            .build());
        }
        return ResponseEntity.ok(
                CheckSocialAccountResponse.builder()
                        .message("successfully")
                        .quantity(this.facebookService.getAllUsers().size())
                        .build());
    }
}
